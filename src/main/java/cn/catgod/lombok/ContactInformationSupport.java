package cn.catgod.lombok;

import lombok.Data;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/17
 */
@Data
public class ContactInformationSupport implements HasContractInformation{

    private String firstName;
    private String lastName;
    private String phoneNr;

    @Override
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
