package jp.co.sample.emp_management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全件取得します.
	 * 
	 * @return　従業員情報一覧
	 */
	public List<Employee> showList() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}
	
	@JsonProperty("employeeNameList")
	public List<String> findAllName(){
		List<Employee> employeeList = employeeRepository.findAll();
		
		List<String> employeeNameList = new ArrayList<>();
		for (Employee employee : employeeList) {
			employeeNameList.add(employee.getName());
		}
		return employeeNameList;
	}
	
	/**
	 * 従業員情報を取得します.
	 * 
	 * @param id ID
	 * @return 従業員情報
	 * @throws 検索されない場合は例外が発生します
	 */
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}
	
	/**
	 * 従業員情報を更新します.
	 * 
	 * @param employee　更新した従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
	
	public List<Employee> findByWord(String word){
		return employeeRepository.findByWord(word);
	}
	
	/**
	 * 従業員のデータ件数を取得する.
	 * @return 従業員のデータ件数を返す
	 */
	public int countData() {
		return employeeRepository.findAllCount();
	}
	
	/**
	 * ページネーションのページ数の数を1からリストにするメソッド.
	 * @return 1からページ数の1リストを返す
	 */
	public List<Integer> pageList(){
		Integer countData = employeeRepository.findAllCount();
		List<Integer> pageNum = new ArrayList<>();
		if( countData / 10 == 0) {
			for(int i = 1; i<=countData; i++) {
				pageNum.add(i);
			} 
		} else {
			for(int i = 1; i <= countData/10+1; i++) {
				pageNum.add(i);
			}
		}
		return pageNum;
	}
	
	/**
	 * 表示するページの数字から、表示する従業員10件を取得する
	 * @param pageNum ページ数
	 * @return　従業員のリストを返す
	 */
	public List<Employee> findAllPageNum(Integer pageNum){
		return employeeRepository.findAllPageNum(pageNum);
	}
	
	
}
