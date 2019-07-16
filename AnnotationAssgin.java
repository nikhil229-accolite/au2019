package auJava;

import java.lang.annotation.*;  
import java.lang.reflect.*;  



  
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD}) 
@interface MyAnnotation{  
	public int age() default 1;
	public String name() default "";
	

}  
  


public class AnnotationAssgin{
     
	public int age ;
	public String name;
	
	public static void main(String[] args)
	{
		//e@MyAnnotation(age = 15, name = "ajay") 
		AnnotationAssgin ob = new AnnotationAssgin();
		System.out.println(ob.age);
		System.out.print(ob.name);
		
		//MyAnnotation manno=m.getAnnotation(MyAnnotation.class);  
		//AnnotationAssgin awdm = AnnotatedClass.class.getAnnotation(AnnotationAssgin.class);
		
	}
	
}
