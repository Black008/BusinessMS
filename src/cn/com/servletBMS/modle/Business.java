package cn.com.servletBMS.modle;

public class Business {
    String businessId;
    String name;
    String phone;
    String Kinds;
    String address;
    String detailaddress;
    int salaryFrom;
    int salaryTo;
    int salary;
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getKinds() {
        return Kinds;
    }

    public void setKinds(String kinds) {
        Kinds = kinds;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress;
    }

    public int getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(int salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public int getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(int salaryTo) {
        this.salaryTo = salaryTo;
    }



}
