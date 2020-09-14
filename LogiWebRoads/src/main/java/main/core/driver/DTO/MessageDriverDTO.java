package main.core.driver.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.driver.entity.Driver;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDriverDTO {
    private int id;
    private String firstName;
    private String lastName;

    public MessageDriverDTO(Driver d){
        id=d.getId();
        firstName=d.getFirstName();
        lastName=d.getLastName();
    }
}
