package com.cda.jdbc.dao;

import java.util.List;

import com.cda.jdbc.data.AnnualSales;

public interface IAnnualSalesDAO {
	List<AnnualSales> getAllSalesPerYear();
}
