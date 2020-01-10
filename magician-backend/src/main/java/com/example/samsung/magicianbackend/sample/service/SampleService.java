package com.example.samsung.magicianbackend.sample.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.samsung.magicianbackend.base.BaseService;
import com.example.samsung.magicianbackend.sample.data.Sample;
import com.example.samsung.magicianbackend.sample.repository.SampleRepository;

@Service
public class SampleService extends BaseService{

	@Autowired
	private SampleRepository sampleRepository;
	
	public Page<Sample> getList(Pageable pageable) {
		return sampleRepository.findAll(pageable);
	}

	public Optional<Sample> save(Sample sample) {
		return Optional.of(sampleRepository.save(sample));
	}

	public boolean saveAll(ArrayList<Sample> sampleList) {
		try {
			sampleRepository.saveAll(sampleList);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public Optional<Sample> getSample(Long id) {
		return sampleRepository.findById(id);
	}

	public boolean delete(Long id) {
		final Optional<Sample> fetchSample = sampleRepository.findById(id);
		if(fetchSample.isPresent()) {
			sampleRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	public Sample patchSample(Sample sample) {
		final Optional<Sample> fetchSample = sampleRepository.findById(sample.getId());
		if(fetchSample.isPresent()) {
			fetchSample.get().setName(sample.getName());
			fetchSample.get().setDescription(sample.getDescription());
			return sampleRepository.save(fetchSample.get());
		}else {
			return null;
		}
	}
}
