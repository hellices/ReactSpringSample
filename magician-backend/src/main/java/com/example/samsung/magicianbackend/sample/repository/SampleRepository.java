package com.example.samsung.magicianbackend.sample.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.samsung.magicianbackend.sample.data.Sample;

@Repository
public interface SampleRepository extends PagingAndSortingRepository<Sample, Long>{

}
