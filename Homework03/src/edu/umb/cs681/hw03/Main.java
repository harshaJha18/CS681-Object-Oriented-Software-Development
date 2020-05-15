package edu.umb.cs681.hw03;


import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.BinaryOperator;
import java.util.function.BiFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main {
	
	 public static Integer getStudentName(Stream<Student> sStream) {
	        return sStream.map( (Student s)-> s.getName() )
	        		.reduce(0,(result,studentName)-> ++result,
	        		(finalName,intermediateName)->
	        		finalName + intermediateName);
	    }
	
	 private static <T> BinaryOperator<T> nullFriendlyComp(BinaryOperator<T> f) {
     return (T m, T n) -> m == null ? n : f.apply(m, n);
 }

 private static Optional<Integer> getXmostStudentScore(Stream<Student> sStream, BinaryOperator<Integer> moreX) {
    	int val = sStream.map((Student s) -> s.getScore())
         .reduce(null, (nullFriendlyComp(moreX)));
     return Optional.ofNullable(val);
 }
	
    public static void main(String[] args) {

        System.out.printf("*************CS681 Homework 03*************\n");
        List<Student> listStudents = new ArrayList<>();
        
        listStudents.add(new Student("Alice",23,82,"CS681"));
        listStudents.add(new Student("Bob",24,90,"CS680"));
        listStudents.add(new Student("Carol",20, 67,"CS682"));
        listStudents.add(new Student("David",18, 80,"CS682"));
        listStudents.add(new Student("Eric",21, 55,"CS681"));
        listStudents.add(new Student("Frank",22, 49,"CS681"));
        listStudents.add(new Student("Gary",23, 88,"CS682"));
        listStudents.add(new Student("Henry",24, 98,"CS681"));
        listStudents.add(new Student("Ivan",20, 66,"CS680"));
        listStudents.add(new Student("John",21, 52,"CS680"));
        listStudents.add(new Student("Ashley",23,82,"CS681"));
        listStudents.add(new Student("James",24,91,"CS680"));
        listStudents.add(new Student("Noah",20, 62,"CS682"));
        listStudents.add(new Student("Nick",18, 84,"CS682"));
        listStudents.add(new Student("Carl",21, 56,"CS681"));
        listStudents.add(new Student("Robert",22, 42,"CS681"));
        listStudents.add(new Student("Stanley",23, 85,"CS682"));
        listStudents.add(new Student("Jenny",24, 99,"CS681"));
        listStudents.add(new Student("Truman",20, 62,"CS680"));
        listStudents.add(new Student("Ulla",21, 55,"CS680"));
        //Total number of student (Map-Reduce Stream)
        Stream<Student> strStu1= listStudents.stream();
        int getStudentName = getStudentName(strStu1);
        System.out.println("\nTotal Number of Students : "+getStudentName);
        
        // Max age of Student (Reduce-Max)
        Stream<Student> strStu= listStudents.stream();
        int maxStudentScore = getXmostStudentScore(strStu, Integer::max).get();
        System.out.println("\nMax Score of a Student : "+maxStudentScore);
        
        //Min age of Student (Reduce-Min)
        Stream<Student> strStu2= listStudents.stream();
       int minStudentScore = getXmostStudentScore(strStu2, Integer::min).get();
       System.out.println("Min Score of a Student : "+minStudentScore);
     
		// Number of student with age more than 20
		long countAge = listStudents.stream()
				.filter( (Student student)-> student.getAge()>20 )
				.count();
		
		System.out.println("\nNumber of Students with age more than 20 : "+countAge);
		
		// Number of student with age less than 20
		long countAgeL = listStudents.stream()
				.filter( (Student student)-> student.getAge()<20 )
				.count();
		
		System.out.println("Number of Students with age less than 20 : "+countAgeL); 
       
        List<Student> top3Students = listStudents.stream()
                .filter(s -> s.getScore() >= 70)
                .sorted()
                .limit(3)
                .collect(Collectors.toList());
 
        		System.out.println("\nTop 3 Students by Score:");
				top3Students.forEach(s -> System.out.println(s));
			 
			        		
			        		String c = "CS681";
							 List<Student> countStudentsbycourse = listStudents.stream()
						                .filter(s -> s.getCourseName().equals(c))
						                .sorted()
						                .collect(Collectors.toList());
							 			System.out.println("\nList of Students take Course:CS681 :");
						        		countStudentsbycourse.forEach(s -> System.out.println(s));	
    }
    
}

