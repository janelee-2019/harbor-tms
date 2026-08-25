package com.lee.tms.infrastructure.rest.vo;

import java.util.List;
import java.util.Objects;

public abstract class TreeVo<T extends TreeVo<T, E>, E>
{
    protected E id;

    protected E parentId;

    protected List<T> children;

    public E getId()
    {
        return id;
    }

    public void setId(E id)
    {
        this.id = id;
    }

    public E getParentId()
    {
        return parentId;
    }

    public void setParentId(E parentId)
    {
        this.parentId = parentId;
    }

    public List<T> getChildren()
    {
        return children;
    }

    public void setChildren(List<T> children)
    {
        this.children = children;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        TreeVo<?, ?> vo = (TreeVo<?, ?>) obj;
        return Objects.equals(this.id, vo.id);
    }
}
