package ru.bakhuss.library.web.person.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PersonView {

    public String id;

    public String firstName;

    public String secondName;

    public String surname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date birthday;

    public String phone;

    public String email;

    public String description;


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + getId() +
                ";firstName:" + getFirstName() +
                ";secondName:" + getSecondName() +
                ";surname:" + getSurname() +
                ";birthday:" + getBirthday() +
                ";phone:" + getPhone() +
                ";email:" + getEmail() +
                ";description:" + getDescription() +
                "}";
    }
}
