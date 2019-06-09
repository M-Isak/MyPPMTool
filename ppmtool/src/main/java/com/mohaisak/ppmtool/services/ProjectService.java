package com.mohaisak.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohaisak.ppmtool.domain.Project;
import com.mohaisak.ppmtool.exceptions.ProjectIdException;
import com.mohaisak.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository  projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		
		//logic
		
		try {
			
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' Already Exists");
		}
		
	}

	public Project findProjectByIdentifier(String projectId) {
		
		 Project project =  projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		 
		 if (project == null) {
		  throw new ProjectIdException("Project ID '"+projectId.toUpperCase()+"' deos not Exist");

		 }
		 
		return project;
	
}
	
	public Iterable<Project> findAllProjects(){
	
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		
	Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if (project == null) {
			  throw new ProjectIdException("cannot delete project '"+projectId.toUpperCase()+"'. it deos not Exist");
		}
		
		projectRepository.delete(project);
	}
	
	
}
