public class EmployeeRunner {
    public static void main(String[] args) {
        Employee[] employeeDataBase = new Employee[5];

        employeeDataBase[0] = new Employee("Benedict Cumberbatch", "System Administrator", "BeBatch@mailbox.com", "89998539647", 50000, 43);
        employeeDataBase[1] = new Employee("Dwayne Johnson", "Programmer", "DwJonhson@mailbox.com", "89992314565", 43000, 39);
        employeeDataBase[2] = new Employee("Scarlett Johansson", "HR-Specialist", "LettJonan@mailbox.com", "89881546789", 28000, 25);
        employeeDataBase[3] = new Employee("Mark Dacascos", "Game Director", "Marcos@mailbox.com", "89885874695", 55000, 35);
        employeeDataBase[4] = new Employee("Alexander Kuritsyn", "Programmer", "ChikenFillet@mailbox.com", "89996583416", 68000, 66);

        for (Employee emp : employeeDataBase) {
            if (emp.getAge() > 40) {
                emp.getEmployeeInfo();
            }
        }

        System.out.println();
    }
}
