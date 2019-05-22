package type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConstructorToUse
{
    boolean doConstruct() default true;//whether construct or not
    String[] initializedVariable() default {};//variables to initialize
}
