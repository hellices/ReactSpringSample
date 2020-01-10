package com.example.samsung.magicianbackend.sample.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.example.samsung.magicianbackend.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(indexes = {@Index(name ="idx_sample_01", columnList = "name"), @Index(name ="idx_sample_02", columnList = "position")})
public class Sample extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 20)
	private String name;
	@Column(length = 2000)
	private String description;
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private Position position;
	private int age;
	@Column(length = 100)
	private String team;
}
