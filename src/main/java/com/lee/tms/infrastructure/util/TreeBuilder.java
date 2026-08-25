package com.lee.tms.infrastructure.util;

import com.lee.tms.infrastructure.rest.vo.TreeVo;

import java.util.*;
import java.util.stream.Collectors;

public final class TreeBuilder
{
    private TreeBuilder() {}

    public static <T extends TreeVo<T, E>, E> List<T> buildTree(Collection<T> nodes)
    {
        if (nodes == null || nodes.isEmpty())
        {
            return Collections.emptyList();
        }

        // 1. 节点去重（依赖 TreeVo 的 equals/hashCode，防止重复对象引发 $ref）
        List<T> distinctNodes = nodes.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());

        // 2. 收集所有非空 ID，用于快速判断根节点 (O(1) 查找)
        Set<E> allIds = distinctNodes.stream().map(TreeVo::getId).filter(Objects::nonNull).collect(Collectors.toSet());

        // 3. 按 parentId 进行分组 (O(N) 建立节点父子关系映射)
        Map<E, List<T>> parentMap = distinctNodes.stream().filter(node -> node.getParentId() != null).collect(Collectors.groupingBy(TreeVo::getParentId));

        // 4. 一次遍历：为各个节点挂载子节点列表
        for (T node : distinctNodes)
        {
            List<T> children = parentMap.get(node.getId());
            if (children != null && !children.isEmpty())
            {
                node.setChildren(children);
            }
        }

        // 5. 过滤出根节点（parentId 为 null、parentId 不在 ID 集中，或 self-parenting 防御）
        return distinctNodes.stream().filter(node -> node.getParentId() == null || !allIds.contains(node.getParentId()) || Objects.equals(node.getId(), node.getParentId()))
                            .collect(Collectors.toList());
    }
}