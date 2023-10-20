import axios from 'axios';

const EMPLOYEE_BASE_REST_API = 'http://localhost:8080/api/employees';

class EmployeeService {
  getAllEmployees() {
    return axios.get(EMPLOYEE_BASE_REST_API);
  }

  createEmployee(employee) {
    return axios.post(EMPLOYEE_BASE_REST_API, employee);
  }

  getEmployeeById(employeeId) {
    return axios.get(EMPLOYEE_BASE_REST_API + '/' + employeeId);
  }

  updateEmployee(employeeId, employee) {
    return axios.put(EMPLOYEE_BASE_REST_API + '/' + employeeId, employee);
  }

  deleteEmployee(employeeId) {
    return axios.delete(EMPLOYEE_BASE_REST_API + '/' + employeeId);
  }
}

export default new EmployeeService();
