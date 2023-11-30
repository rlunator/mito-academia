package com.mitocode.mitoacademia.service.impl;

import com.mitocode.mitoacademia.dto.EnrollmentCuorseDTO;
import com.mitocode.mitoacademia.model.Enrollment;
import com.mitocode.mitoacademia.model.EnrollmentDetail;
import com.mitocode.mitoacademia.repo.IEnrollmentRepo;
import com.mitocode.mitoacademia.repo.IGeneRepo;
import com.mitocode.mitoacademia.service.IEnrollmentService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl extends CRUDServiceImpl<Enrollment,Integer> implements IEnrollmentService {

    private final IEnrollmentRepo repo;
    
    @Override
    protected IGeneRepo<Enrollment, Integer> getRepo() {
        return repo;
    }


	@Override
	public List<EnrollmentCuorseDTO> getResumeEnrollment() throws Exception {
		List<EnrollmentCuorseDTO> listEnroll = new ArrayList<>();
		Stream<Enrollment> enrollStream = repo.findAll().stream();
		Stream<List<EnrollmentDetail>> lsDStream = enrollStream.map(Enrollment::getDetails);
		Stream<EnrollmentDetail> streamDetail = lsDStream.flatMap(Collection::stream);
		Map<Object,Map<Object,List<EnrollmentDetail>>> mapRes = streamDetail
																	.collect(groupingBy(e-> e.getCourse().getName() ,
																			groupingBy(e -> e.getEnrollment().getStudent().getName())));
		
		listEnroll= mapRes.entrySet()
						 	.stream()
						 	.flatMap(e ->{
						 		EnrollmentCuorseDTO resul = new EnrollmentCuorseDTO();
								List<String> studentLis = new ArrayList<>();
								resul.setNameOfCourse(e.getKey().toString());
								e.getValue().entrySet().forEach(it ->{
									List<EnrollmentDetail> lis =it.getValue();
									lis.forEach(item -> {
										StringBuilder sb = new StringBuilder();
										sb.append(item.getEnrollment().getStudent().getName())
										  .append(" ")
										  .append(item.getEnrollment().getStudent().getLastName());
										studentLis.add(sb.toString());
									});
								});
								resul.setStudents(studentLis.stream().distinct().toList());
								return Stream.of(resul);
						 	}).toList();
		return listEnroll;
	}
}
