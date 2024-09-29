package com.rookie.stack.im.friend.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户申请表
 * @TableName user_apply
 */
@TableName(value ="user_apply")
@Data
public class UserApply implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 申请人uid
     */
    private Long uid;

    /**
     * 申请类型 1加好友
     */
    private Integer type;

    /**
     * 接收人uid
     */
    private Long targetId;

    /**
     * 申请信息
     */
    private String msg;

    /**
     * 申请状态 1待审批 2同意
     */
    private Integer status;

    /**
     * 阅读状态 1未读 2已读
     */
    private Integer readStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserApply other = (UserApply) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getTargetId() == null ? other.getTargetId() == null : this.getTargetId().equals(other.getTargetId()))
            && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getReadStatus() == null ? other.getReadStatus() == null : this.getReadStatus().equals(other.getReadStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTargetId() == null) ? 0 : getTargetId().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getReadStatus() == null) ? 0 : getReadStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", type=").append(type);
        sb.append(", targetId=").append(targetId);
        sb.append(", msg=").append(msg);
        sb.append(", status=").append(status);
        sb.append(", readStatus=").append(readStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}