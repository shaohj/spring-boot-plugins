package com.sprboot.ex.ormplugin.jpa.muldb.model.db2;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 编  号：
 * 名  称：User2
 * 描  述：
 * 完成日期：2020/4/4 18:17
 * @author：felix.shao
 */
@Data
@Entity
@EntityListeners({AuditingEntityListener.class})
@Table(name="user")
public class User2 {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @CreatedBy
    @Column(name="create_by", updatable=false)
    private String createBy;

    @CreatedDate
    @Column(name="create_time", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @LastModifiedBy
    @Column(name="last_modify_by")
    private String lastModifyBy;

    @LastModifiedDate
    @Column(name="last_modify_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyTime;

    @Column(name="delete_flag")
    private String deleteFlag;

}
