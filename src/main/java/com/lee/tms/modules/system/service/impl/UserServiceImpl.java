package com.lee.tms.modules.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.config.props.AuthProps;
import com.lee.tms.infrastructure.auth.AuthContext;
import com.lee.tms.infrastructure.auth.AuthUser;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.system.dto.body.UserAddBody;
import com.lee.tms.modules.system.dto.body.UserPasswordUpdateBody;
import com.lee.tms.modules.system.dto.body.UserUpdateBody;
import com.lee.tms.modules.system.dto.query.UserListQuery;
import com.lee.tms.modules.system.dto.query.UserPageQuery;
import com.lee.tms.modules.system.dto.vo.UserInfoVo;
import com.lee.tms.modules.system.dto.vo.UserSimpleVo;
import com.lee.tms.modules.system.entity.Dept;
import com.lee.tms.modules.system.entity.Role;
import com.lee.tms.modules.system.entity.User;
import com.lee.tms.modules.system.entity.UserRole;
import com.lee.tms.modules.system.mapper.UserMapper;
import com.lee.tms.modules.system.service.DeptService;
import com.lee.tms.modules.system.service.RoleService;
import com.lee.tms.modules.system.service.UserRoleService;
import com.lee.tms.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService
{
    @Lazy
    @Autowired
    private DeptService deptService;

    @Lazy
    @Autowired
    private RoleService roleService;

    @Lazy
    @Autowired
    private UserRoleService userRoleService;

    @Lazy
    @Autowired
    private AuthProps authProps;

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void addUser(UserAddBody body)
    {
        // 不能添加超级管理员角色
        if (body.getRoleIds().contains(0L))
        {
            throw new RestException(RestCode.SYS_ERR, "不允许添加超级管理员用户");
        }

        User user = new User();
        BeanUtil.copyProperties(body, user);

        checkLoginNameUniqueOrThrow(user);
        checkEmployeeNameUniqueOrThrow(user);

        checkDeptExists(user.getDeptId());

        // 检查角色是否存在
        List<Role> roles = roleService.list(Wrappers.<Role>lambdaQuery().in(Role::getId, body.getRoleIds()));
        if (roles.size() < body.getRoleIds().size())
        {
            throw new RestException(RestCode.SYS_ERR, "角色不存在");
        }

        String salt = RandomUtil.randomString(16);
        String md5Pass = DigestUtil.md5Hex(body.getLoginPassword() + salt);
        user.setSalt(salt);
        user.setLoginPassword(md5Pass);
        LocalDateTime now = LocalDateTime.now();
        user.setCreateDate(now);
        user.setUpdateDate(now);
        user.setPasswordUpdateDate(now);

        AuthUser authUser = AuthContext.getAuthUser();
        user.setCreatorId(authUser.getId());
        user.setUpdaterId(authUser.getId());

        user.insert();

        // 插入用户与角色关联信息
        buildUserRoles(user.getId(), body.getRoleIds()).forEach(UserRole::insert);
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void updateUser(UserUpdateBody body)
    {
        // 不能编辑超级管理员角色
        if (body.getRoleIds().contains(0L))
        {
            throw new RestException(RestCode.SYS_ERR, "不允许编辑超级管理员");
        }

        User user = new User();
        BeanUtil.copyProperties(body, user);

        checkLoginNameUniqueOrThrow(user);
        checkEmployeeNameUniqueOrThrow(user);

        checkDeptExists(user.getDeptId());

        // 检查角色是否存在
        List<Role> roles = roleService.list(Wrappers.<Role>lambdaQuery().in(Role::getId, body.getRoleIds()));
        if (roles.size() < body.getRoleIds().size())
        {
            throw new RestException(RestCode.SYS_ERR, "角色不存在");
        }

        LocalDateTime now = LocalDateTime.now();
        user.setUpdateDate(now);

        AuthUser authUser = AuthContext.getAuthUser();
        user.setUpdaterId(authUser.getId());

        update(user, Wrappers.<User>lambdaUpdate().set(StrUtil.isBlank(user.getRemark()), User::getRemark, null).set(StrUtil.isBlank(user.getEmployeeCode()), User::getEmployeeCode, null)
                             .set(StrUtil.isBlank(user.getEmployeeTel()), User::getEmployeeTel, null).set(StrUtil.isBlank(user.getEmployeeEmail()), User::getEmployeeEmail, null)
                             .set(StrUtil.isBlank(user.getEmployeeAddress()), User::getEmployeeAddress, null).set(StrUtil.isBlank(user.getEmployeePicture()), User::getEmployeePicture, null)
                             .set(StrUtil.isBlank(user.getEmployeeSex()), User::getEmployeeSex, null).eq(User::getId, user.getId()));

        // 插入用户与角色关联信息
        // 先删除用户与角色关联信息
        userRoleService.remove(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, user.getId()));
        buildUserRoles(user.getId(), body.getRoleIds()).forEach(UserRole::insert);
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void deleteUser(Long userId)
    {
        User user = getUserByIdOrThrow(userId);
        user.setDelFlag("1");
        user.updateById();

        // 级联删除用户角色关联信息
        // userRoleService.remove(Wrappers.<UserRole>lambdaUpdate().eq(UserRole::getUserId, userId));
    }

    @Override
    public void updateLoginPassword(UserPasswordUpdateBody body)
    {
        AuthUser authUser = AuthContext.getAuthUser();
        // 不能编辑超级管理员角色
        if (authUser.getId() == 0L)
        {
            throw new RestException(RestCode.SYS_ERR, "不允许编辑超级管理员");
        }
        User user = getUserByIdOrThrow(authUser.getId());
        String salt = RandomUtil.randomString(16);
        String md5Pass = DigestUtil.md5Hex(body.getLoginPassword() + salt);
        user.setSalt(salt);
        user.setLoginPassword(md5Pass);
        LocalDateTime now = LocalDateTime.now();
        user.setUpdateDate(now);
        user.setPasswordUpdateDate(now);

        user.setUpdaterId(authUser.getId());

        user.updateById();
    }

    @Override
    public void resetLoginPassword(Long userId)
    {
        // 不能编辑超级管理员角色
        if (userId == 0L)
        {
            throw new RestException(RestCode.SYS_ERR, "不允许编辑超级管理员");
        }

        getUserByIdOrThrow(userId);

        User user = getUserByIdOrThrow(userId);
        String salt = RandomUtil.randomString(16);
        String md5Pass = DigestUtil.md5Hex(authProps.getDefaultLoginPassword() + salt);
        user.setSalt(salt);
        user.setLoginPassword(md5Pass);
        LocalDateTime now = LocalDateTime.now();
        user.setUpdateDate(now);
        user.setPasswordUpdateDate(now);

        AuthUser authUser = AuthContext.getAuthUser();
        user.setUpdaterId(authUser.getId());

        user.updateById();
    }

    @Override
    public List<UserSimpleVo> listUser(UserListQuery query)
    {
        if (StrUtil.isBlank(query.getEmployeeName()))
        {
            throw new RestException("查询条件不能为空");
        }
        // 1. 构建 userWrapper 基础查询条件
        LambdaQueryWrapper<User> userWrapper = Wrappers.lambdaQuery(User.class).ne(User::getId, 0).eq(User::getDelFlag, 0);

        if (StrUtil.isNotBlank(query.getEmployeeName()))
        {
            userWrapper.and(w -> w.likeRight(User::getLoginName, query.getEmployeeName()).or().like(User::getEmployeeName, query.getEmployeeName()));
        }

        // 2. 通过 inSql 拼入中间表与角色表的过滤逻辑
        String sql = "SELECT ur.USER_ID FROM SYS_USER_ROLE ur " + "INNER JOIN SYS_ROLE r ON ur.ROLE_ID = r.ID " + "WHERE r.ROLE_LEVEL > ";
        userWrapper.inSql(User::getId, sql + AuthUser.LEVEL_PROJECT_MANAGER);

        // 3. 直接调用 MP 标准单表查询（底层自动处理 JOIN 逻辑）
        List<User> users = baseMapper.selectList(userWrapper);

        return users.stream().map(UserSimpleVo::fromModel).collect(Collectors.toList());
    }

    @Override
    public DefaultPageVo<User> paginateUser(UserPageQuery query)
    {
        IPage<User> page = new Page<>(query.getPageNo(), query.getPageSize());

        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.ne("u.id", 0)
               .eq("u.del_flag", "0")
               .like(StrUtil.isNotEmpty(query.getLoginName()),"u.login_name", query.getLoginName())
               .like(StrUtil.isNotEmpty(query.getEmployeeName()), "u.employee_name", query.getEmployeeName())
               .orderByDesc("u.id");

        IPage<User> users = baseMapper.selectUserPage(page, wrapper);

        return new DefaultPageVo<>(query.getPageNo(),
                                   query.getPageSize(),
                                   userRoleService.count(),
                                   users.getRecords());
    }

    @Override
    public UserInfoVo getUserInfo(Long userId)
    {
        if (userId == 0)
        {
            throw new RestException(RestCode.SYS_ERR, "超级管理员不允许查看");
        }

        User user = baseMapper.selectUserById(userId);
        if (user == null)
        {
            throw new RestException(RestCode.SYS_ERR, "用户不存在 [ID = {}]", userId);
        }

        return UserInfoVo.fromModel(user);
    }

    @Override
    public List<User> getAllUsers()
    {
        return baseMapper.selectAllUserList();
    }

    private List<UserRole> buildUserRoles(Long userId, List<Long> roleIds)
    {
        List<UserRole> userRoles = new ArrayList<>(roleIds.size());
        for (Long roleId : roleIds)
        {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoles.add(userRole);
        }
        return userRoles;
    }

    private void checkDeptExists(Long deptId)
    {
        if (deptService.count(Wrappers.<Dept>lambdaQuery().eq(Dept::getId, deptId)) < 1)
        {
            throw new RestException(RestCode.SYS_ERR, "部门不存在 [ID = {}]", deptId);
        }
    }

    private void checkLoginNameUniqueOrThrow(User user)
    {
        Wrapper<User> wrapper = Wrappers.<User>lambdaQuery().eq(User::getLoginName, user.getLoginName()).ne(user.getId() != null, User::getId, user.getId());
        if (count(wrapper) > 0)
        {
            throw new RestException(RestCode.SYS_ERR, "登录用户名重复 [loginName = {}]", user.getLoginName());
        }
    }

    private void checkEmployeeNameUniqueOrThrow(User user)
    {
        Wrapper<User> wrapper = Wrappers.<User>lambdaQuery().eq(User::getLoginName, user.getLoginName()).ne(user.getId() != null, User::getId, user.getId());
        if (count(wrapper) > 0)
        {
            throw new RestException(RestCode.SYS_ERR, "员工姓名重复 [loginName = {}]", user.getLoginName());
        }
    }

    private User getUserByIdOrThrow(Long userId)
    {
        User user = getOne(Wrappers.<User>lambdaQuery().eq(User::getDelFlag, "0").ne(User::getId, 0).eq(User::getId, userId));
        if (user == null)
        {
            throw new RestException(RestCode.SYS_ERR, "用户不存在 [ID = {}]", userId);
        }
        return user;
    }
}
