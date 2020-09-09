package main.core.employee.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.Security.entity.User;
import main.core.employee.entity.Employee;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private int id;
  private   String login;
  private   String password;

  public EmployeeDTO(Employee e){
      id=e.getId();
      login=e.getUser().getUsername();
      password=e.getUser().getPassword();
  }

  public Employee toEmployee(){
      Employee employee= new Employee(new User(login,password,true,null));
      employee.setId(id);
      return employee;
  }

}
