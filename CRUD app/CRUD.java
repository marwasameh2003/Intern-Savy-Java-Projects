import java.util.*;

class employee {
    private int empno;
    private String ename;
    private int salary;

    employee(int empno, String ename, int salary) {
        this.empno = empno;
        this.ename = ename;
        this.salary = salary;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String name) {
        this.ename = name;
    }

    public String toString() {
        return empno + " " + ename + " " + salary;
    }
}

class CRUD {
    public static void main(String[] args) {
        Collection<employee> c = new ArrayList<employee>();
        Scanner s = new Scanner(System.in);
        int ch = -1;
        do {
            System.out.println("1.Insert");
            System.out.println("2.Display");
            System.out.println("3.Search");
            System.out.println("4.Delete");
            System.out.println("5.Update");
            System.out.println("Please enter the number of operation you want");
            System.out.print("Enter Your choice: ");
            ch = s.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("Enter Employee number: ");
                    int eno = s.nextInt();
                    System.out.println("Enter Employee name: ");
                    String enm = s.next();
                    System.out.println("Enter Employee salary: ");
                    int sal = s.nextInt();
                    c.add(new employee(eno, enm, sal));
                    System.out.println("Employee has been added successfully!");
                    break;
                case 2:
                    System.out.println("-----------------------------------------------");
                    System.out.println("-----------------------------------");
                    System.out.println("EMP.ID     EMP.NAME    EMP.SALARY");
                    Iterator<employee> i = c.iterator();
                    while (i.hasNext()) {
                        employee e = i.next();
                        System.out.println(e.getEmpno()+"        " + e.getEname() + "        "+e.getSalary());
                        System.out.println("-----------------------------------");

                    }
                    System.out.println("-----------------------------------------------");

                    break;
                case 3:
                    boolean found = false;
                    System.out.println("Enter Employee number to search for: ");
                    int emp = s.nextInt();
                    System.out.println("--------------------------------");
                    i = c.iterator();
                    while (i.hasNext()) {
                        employee e = i.next();
                        if (e.getEmpno() == emp) {
                            found = true;
                            System.out.println(e.getEmpno()+"        " + e.getEname() + "        "+e.getSalary());

                        }
                    }
                    if (!found) {
                        System.out.println("The employee you are searching for doesn't exist");
                    }
                    System.out.println("--------------------------------");
                    break;
                case 4:
                    found = false;
                    System.out.println("Enter Employee number to Delete : ");
                    emp = s.nextInt();
                    System.out.println("--------------------------------");
                    i = c.iterator();
                    while (i.hasNext()) {
                        employee e = i.next();
                        if (e.getEmpno() == emp) {
                            found = true;
                            i.remove();
                            System.out.println("Employee is Deleted Successfully");
                        }
                    }
                    if (!found) {
                        System.out.println("The employee you want to delete doesn't exist");
                    }
                    System.out.println("--------------------------------");
                    break;
                case 5:
                    found = false;
                    System.out.println("Enter Employee number to update : ");
                    emp = s.nextInt();
                    i = c.iterator();
                    while (i.hasNext()) {
                        employee e = i.next();
                        if (e.getEmpno() == emp) {
                            found = true;
                            System.out.println("Enter number of field you want to change: ");
                            System.out.println("1.Employee Number: ");
                            System.out.println("2.Employee Name: ");
                            System.out.println("3.Employee Salary: ");
                            int change = s.nextInt();
                            switch (change) {
                                case 1:
                                    System.out.println("Enter the new number for this employee: ");
                                    int num = s.nextInt();
                                    e.setEmpno(num);
                                    System.out.println("The employee's number has been updated successfully");
                                    System.out.println("--------------------------------");
                                    System.out.println(e);
                                    System.out.println("--------------------------------");
                                    break;
                                case 2:
                                    System.out.println("Enter the new name for this employee: ");
                                    String nm = s.next();
                                    e.setEname(nm);
                                    System.out.println("The employee's name has been updated successfully");
                                    System.out.println("--------------------------------");
                                    System.out.println(e);
                                    System.out.println("--------------------------------");

                                    break;
                                case 3:
                                    System.out.println("Enter the new salary for this employee: ");
                                    num = s.nextInt();
                                    e.setSalary(num);
                                    System.out.println("The employee's salary has been updated successfully");
                                    System.out.println("--------------------------------");
                                    System.out.println(e);
                                    System.out.println("--------------------------------");

                                    break;
                            }
                        } else {
                            System.out.println("--------------------------------");

                            System.out.println("The employee you want to update doesn't exist");
                            System.out.println("--------------------------------");

                        }
                    }
            }
        } while (ch != 0);
        System.out.println("Thank you for using our system!");
    }
}