package element;

import annotation.FindElement;
import exception.ElementInitException;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

public class ElementFactory {

    public static void initElements(WebDriver driver, Object object) {
        Class cls = object.getClass();

        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {

            FindElement annotation = f.getAnnotation(FindElement.class);
            if (FacadeElement.class.isAssignableFrom(f.getType()) && annotation != null) {

                String value = annotation.value();
                LocatorType type = annotation.type();
                String name = f.getName();
                ExpectedCondition condition = annotation.condition();

                ElementInfo info = new ElementInfo(value, type, name, condition);
                FacadeElement element = new FacadeElementImp(driver, info);

                f.setAccessible(true);

                try {
                    f.set(object, element);
                } catch (IllegalAccessException e) {
                    throw new ElementInitException(e.getMessage(), e);
                }

            }
        }
    }
}
