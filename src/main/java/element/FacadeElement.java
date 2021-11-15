package element;

public interface FacadeElement {

    void hasText(String name);

    void click(long timeout);

    void click();

    void type(String value);

    void typeAndEnter(String value);

    void select(String value);
}
