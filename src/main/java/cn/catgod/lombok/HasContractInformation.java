package cn.catgod.lombok;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/17
 */
public interface HasContractInformation {

    String getFirstName();
    void setFirstName(String firstName);

    String getFullName();

    String getLastName();
    void setLastName(String lastName);

    String getPhoneNr();
    void setPhoneNr(String phoneNr);
}
