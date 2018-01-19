package com.alphaz.core.pojo.entity;

import com.alphaz.core.constant.DataState;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * 持久化对象的基类,所有要持久化到数据库的对象继承该类或其子类
 *
 * @author HuangJian
 * @since May 19, 2010
 */
@MappedSuperclass
public abstract class BaseDO {

    /**
     * 主键，UUID
     */
    private Long id;

    /**
     * 逻辑删除
     */
    private DataState state;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 更新人
     */
    private Long updateBy;

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "state", nullable = false, columnDefinition = "tinyint default 1", length = 1)
    public DataState getState() {
        return state;
    }

    public void setState(DataState state) {
        this.state = state;
    }

    @Basic
    @Column(name = "createtime", columnDefinition = "datetime default CURRENT_TIMESTAMP")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "updatetime", columnDefinition = "datetime on update CURRENT_TIMESTAMP")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    @Basic
    @Column(name = "createby")
    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "updateby")
    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

}
