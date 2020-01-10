package com.example.samsung.magicianbackend.sample.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.samsung.magicianbackend.base.BaseController;
import com.example.samsung.magicianbackend.base.PageRequest;
import com.example.samsung.magicianbackend.sample.data.Sample;
import com.example.samsung.magicianbackend.sample.service.SampleService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/sample")
@Log4j2
public class SampleController extends BaseController{

	@Autowired
	private SampleService sampleService;
	
	@GetMapping("/list")
	public ResponseEntity <Page<Sample>> sampleList(final PageRequest pageable) {
		log.info(pageable.toString());
		return new ResponseEntity<>(sampleService.getList(pageable.of()), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Sample> getSample(@PathVariable Long id) {
		Optional<Sample> sample = sampleService.getSample(id);
		if(sample.isPresent()) {
			return new ResponseEntity<Sample>(sample.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Sample>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping
	public ResponseEntity<Sample> saveSample(@RequestBody(required = true) Sample sample){
		log.info("sample : {}", sample);
		Optional<Sample> returnSample = sampleService.save(sample);
		if(!returnSample.isPresent()) {
			return new ResponseEntity<Sample>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Sample>(returnSample.get(), HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<Sample> patchSample(@RequestBody(required = true) Sample sample){
		Sample updateSample = sampleService.patchSample(sample);
		if(updateSample != null) {
			return new ResponseEntity<Sample>(updateSample, HttpStatus.OK);
		}else {
			return new ResponseEntity<Sample>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/saveAll")
	public ResponseEntity<Void> saveAllSample(@RequestBody(required = true) ArrayList<Sample> sampleList){
		boolean flag = sampleService.saveAll(sampleList);
		if(!flag) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSample(@PathVariable Long id){
		boolean flag = sampleService.delete(id);
		if(!flag) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	
	
}
