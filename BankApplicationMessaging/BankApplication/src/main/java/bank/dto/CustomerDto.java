package bank.dto;


public class CustomerDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerDto(String name) {
        this.name = name;
    }
}
