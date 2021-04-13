package com.kostya.coupons.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kostya.coupons.dao.ICouponsDao;
import com.kostya.coupons.enums.ErrorType;
import com.kostya.coupons.exceptions.ApplicationException;

@Component
public class DeleteCouponsTask{
	
	@Autowired
	private ICouponsDao couponsDao;

	@Scheduled(cron = "0 0 0 * * *")
	public void deleteOutdatedCoupons() throws ApplicationException {
		try {
		this.couponsDao.deleteOutdatedCoupons();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to delete outdated coupons");
		}
	}
}
