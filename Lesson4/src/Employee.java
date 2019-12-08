public class Employee {
        private String fio;
        private String position;
        private String email;
        private String phone;
        private int salary;
        private int age;

        public Employee(String fio, String position, String email, String phone, int salary, int age) {
            setFio(fio);
            setPosition(position);
            setEmail(email);
            setPhone(phone);
            setSalary(salary);
            setAge(age);
        }

        public String toString() {
            return "Employee fio = " + getFio() + ", position = " + getPosition() + ", email = " + getEmail() + ", phone = " + getPhone() + ", salary = " + getSalary() + ", age = " + getAge();
        }

        public void getEmployeeInfo() {
            System.out.println(this);
        }

        public String getFio() {
            return fio;
        }

        public void setFio(String fio) {
            this.fio = fio;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

