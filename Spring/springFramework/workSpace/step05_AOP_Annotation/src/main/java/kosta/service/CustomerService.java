package kosta.service;

public interface CustomerService {
	int select();

	String selectById(String id);

	void update(String id, String name);
}