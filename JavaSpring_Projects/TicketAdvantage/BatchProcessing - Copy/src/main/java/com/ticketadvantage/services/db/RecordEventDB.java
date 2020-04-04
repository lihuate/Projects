/**
 * 
 */
package com.ticketadvantage.services.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ticketadvantage.services.errorhandling.BatchErrorCodes;
import com.ticketadvantage.services.errorhandling.BatchErrorMessage;
import com.ticketadvantage.services.errorhandling.BatchException;
import com.ticketadvantage.services.model.AccountEvent;
import com.ticketadvantage.services.model.MlRecordEvent;
import com.ticketadvantage.services.model.SpreadRecordEvent;
import com.ticketadvantage.services.model.TotalRecordEvent;

/**
 * @author jmiller
 *
 */
public class RecordEventDB extends BaseDB {
	private static final Logger LOGGER = Logger.getLogger(RecordEventDB.class);

	/**
	 * 
	 */
	public RecordEventDB() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param recordEvent
	 * @return
	 * @throws SQLException
	 */
	public boolean checkDuplicateSpreadEvent(SpreadRecordEvent recordEvent) throws BatchException {
		LOGGER.info("Entering checkDuplicateSpreadEvent()");
		boolean retValue = false;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		try {
			StringBuffer sb = new StringBuffer(200);
			sb.append("SELECT sre.id FROM spreadrecordevents sre ")
					.append("WHERE sre.accountid = ? ")
					.append("AND sre.eventdatetime = ? ")
					.append("AND sre.eventid = ? ")
					.append("AND sre.eventname = ? ")
					.append("AND sre.eventtype = ? ")
					.append("AND sre.groupid = ? ")
					.append("AND sre.sport = ? ")
					.append("AND sre.userid = ? ");

			LOGGER.debug("conn: " + conn);
			stmt = conn.prepareStatement(sb.toString());
			stmt.setLong(1, recordEvent.getAccountid());
			stmt.setTimestamp(2, new java.sql.Timestamp(recordEvent.getEventdatetime().getTime()));
			stmt.setLong(3, recordEvent.getEventid());
			stmt.setString(4, recordEvent.getEventname());
			stmt.setString(5, recordEvent.getEventtype());
			stmt.setLong(6, recordEvent.getGroupid());
			stmt.setString(7, recordEvent.getSport());
			stmt.setLong(8, recordEvent.getUserid());
			resultSet = stmt.executeQuery();
			
			if (resultSet.next()) {
				closeStreams(stmt, resultSet);
				retValue = true;
			} else {
				closeStreams(stmt, resultSet);
			}
		} catch (SQLException sqle) {
			LOGGER.error(sqle);
			LOGGER.error(sqle.getMessage());
			try {
				conn.rollback();
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
				throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, "SQL Exception");
			}
			throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, sqle.getMessage());
		} finally {
			try {
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
		
		LOGGER.info("Exiting checkDuplicateSpreadEvent()");
		return retValue;
	}

	/**
	 * 
	 * @param recordEvent
	 * @return
	 * @throws BatchException
	 */
	public boolean checkDuplicateTotalEvent(TotalRecordEvent recordEvent) throws BatchException {
		LOGGER.info("Entering checkDuplicateTotalEvent()");
		boolean retValue = false;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		try {	
			StringBuffer sb = new StringBuffer(200);
			sb.append("SELECT tre.id FROM totalrecordevents tre ")
					.append("WHERE tre.accountid = ? ")
					.append("AND tre.eventdatetime = ? ")
					.append("AND tre.eventid = ? ")
					.append("AND tre.eventname = ? ")
					.append("AND tre.eventtype = ? ")
					.append("AND tre.groupid = ? ")
					.append("AND tre.sport = ? ")
					.append("AND tre.userid = ? ");

			LOGGER.debug("conn: " + conn);
			stmt = conn.prepareStatement(sb.toString());
			stmt.setLong(1, recordEvent.getAccountid());
			stmt.setTimestamp(2, new java.sql.Timestamp(recordEvent.getEventdatetime().getTime()));
			stmt.setLong(3, recordEvent.getEventid());
			stmt.setString(4, recordEvent.getEventname());
			stmt.setString(5, recordEvent.getEventtype());
			stmt.setLong(6, recordEvent.getGroupid());
			stmt.setString(7, recordEvent.getSport());
			stmt.setLong(8, recordEvent.getUserid());
			resultSet = stmt.executeQuery();
			
			if (resultSet.next()) {
				closeStreams(stmt, resultSet);
				retValue = true;
			} else {
				closeStreams(stmt, resultSet);
			}
		} catch (SQLException sqle) {
			LOGGER.error(sqle);
			LOGGER.error(sqle.getMessage());
			try {
				conn.rollback();
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
				throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, "SQL Exception");
			}
			throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, sqle.getMessage());
		} finally {
			try {
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
		
		LOGGER.info("Exiting checkDuplicateTotalEvent()");
		return retValue;
	}

	/**
	 * 
	 * @param recordEvent
	 * @return
	 * @throws BatchException
	 */
	public boolean checkDuplicateMlEvent(MlRecordEvent recordEvent) throws BatchException {
		LOGGER.info("Entering checkDuplicateMlEvent()");
		boolean retValue = false;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		try {
			StringBuffer sb = new StringBuffer(200);
			sb.append("SELECT mre.id FROM mlrecordevents mre ")
					.append("WHERE mre.accountid = ? ")
					.append("AND mre.eventdatetime = ? ")
					.append("AND mre.eventid = ? ")
					.append("AND mre.eventname = ? ")
					.append("AND mre.eventtype = ? ")
					.append("AND mre.groupid = ? ")
					.append("AND mre.sport = ? ")
					.append("AND mre.userid = ?");
	
			stmt = conn.prepareStatement(sb.toString());
			stmt.setLong(1, recordEvent.getAccountid());
			stmt.setTimestamp(2, new java.sql.Timestamp(recordEvent.getEventdatetime().getTime()));
			stmt.setLong(3, recordEvent.getEventid());
			stmt.setString(4, recordEvent.getEventname());
			stmt.setString(5, recordEvent.getEventtype());
			stmt.setLong(6, recordEvent.getGroupid());
			stmt.setString(7, recordEvent.getSport());
			stmt.setLong(8, recordEvent.getUserid());
			resultSet = stmt.executeQuery();
			
			if (resultSet.next()) {
				closeStreams(stmt, resultSet);
				retValue = true;
			} else {
				closeStreams(stmt, resultSet);
			}
		} catch (SQLException sqle) {
			LOGGER.error(sqle);
			LOGGER.error(sqle.getMessage());
			try {
				conn.rollback();
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
				throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, "SQL Exception");
			}
			throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, sqle.getMessage());
		} finally {
			try {
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
		
		LOGGER.info("Exiting checkDuplicateTotalEvent()");
		return retValue;
	}

	/**
	 * 
	 * @param recordEvent
	 * @return
	 * @throws BatchException
	 */
	public Long setSpreadEvent(SpreadRecordEvent recordEvent) throws BatchException {
		LOGGER.info("Entering setSpreadEvent()");
		LOGGER.info("SpreadRecordEvent: " + recordEvent);
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
		try {
			recordEvent.setId(null);
			StringBuffer sb = new StringBuffer(200);
			sb.append("INSERT into spreadrecordevents (accountid, amount, attempts, attempttime, ")
					.append("currentattempts, datecreated, datemodified, datentime, eventdatetime, ")
					.append("eventid, eventid1, eventid2, eventname, eventteam1, eventteam2, ")
					.append("eventtype, groupid, iscompleted, sport, userid, wtype, ")
					.append("spreadinputfirstone, spreadinputfirsttwo, ")
					.append("spreadinputjuicefirstone, spreadinputjuicefirsttwo, ")
					.append("spreadinputjuicesecondone, spreadinputjuicesecondtwo, ")
					.append("spreadinputsecondone, spreadinputsecondtwo, ")
					.append("spreadjuiceplusminusfirstone, spreadjuiceplusminusfirsttwo, ")
					.append("spreadjuiceplusminussecondone, spreadjuiceplusminussecondtwo, ")
					.append("spreadplusminusfirstone, spreadplusminusfirsttwo, ")
					.append("spreadplusminussecondone, spreadplusminussecondtwo) ")
					.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ")
					.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	
			stmt = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, checkLong(recordEvent.getAccountid()));
			stmt.setString(2, checkString(recordEvent.getAmount()));
			stmt.setInt(3, checkInteger(recordEvent.getAttempts()));
			stmt.setTimestamp(4, new java.sql.Timestamp(checkTimestamp(recordEvent.getAttempttime()).getTime()));
			stmt.setInt(5, checkInteger(recordEvent.getCurrentattempts()));
			stmt.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
			stmt.setTimestamp(7, new java.sql.Timestamp(new Date().getTime()));
			stmt.setTimestamp(8, new java.sql.Timestamp(checkTimestamp(recordEvent.getDatentime()).getTime()));
			stmt.setTimestamp(9, new java.sql.Timestamp(checkTimestamp(recordEvent.getEventdatetime()).getTime()));
			stmt.setInt(10, checkInteger(recordEvent.getEventid()));
			stmt.setInt(11, checkInteger(recordEvent.getEventid1()));
			stmt.setInt(12, checkInteger(recordEvent.getEventid2()));
			stmt.setString(13, checkString(recordEvent.getEventname()));
			stmt.setString(14, checkString(recordEvent.getEventteam1()));
			stmt.setString(15, checkString(recordEvent.getEventteam2()));
			stmt.setString(16, checkString(recordEvent.getEventtype()));
			stmt.setLong(17, checkLong(recordEvent.getGroupid()));
			stmt.setBoolean(18, checkBoolean(recordEvent.getIscompleted()));
			stmt.setString(19, checkString(recordEvent.getSport()));
			stmt.setLong(20, checkLong(recordEvent.getUserid()));
			stmt.setString(21, checkString(recordEvent.getWtype()));
			
			stmt.setString(22, checkString(recordEvent.getSpreadinputfirstone()));
			stmt.setString(23, checkString(recordEvent.getSpreadinputfirsttwo()));
			stmt.setString(24, checkString(recordEvent.getSpreadinputjuicefirstone()));
			stmt.setString(25, checkString(recordEvent.getSpreadinputjuicefirsttwo()));
			stmt.setString(26, checkString(recordEvent.getSpreadinputjuicesecondone()));
			stmt.setString(27, checkString(recordEvent.getSpreadinputjuicesecondtwo()));
			stmt.setString(28, checkString(recordEvent.getSpreadinputsecondone()));
			stmt.setString(29, checkString(recordEvent.getSpreadinputsecondtwo()));
			stmt.setString(30, checkString(recordEvent.getSpreadjuiceplusminusfirstone()));
			stmt.setString(31, checkString(recordEvent.getSpreadjuiceplusminusfirsttwo()));
			stmt.setString(32, checkString(recordEvent.getSpreadjuiceplusminussecondone()));
			stmt.setString(33, checkString(recordEvent.getSpreadjuiceplusminussecondtwo()));
			stmt.setString(34, checkString(recordEvent.getSpreadplusminusfirstone()));
			stmt.setString(35, checkString(recordEvent.getSpreadplusminusfirsttwo()));
			stmt.setString(36, checkString(recordEvent.getSpreadplusminussecondone()));
			stmt.setString(37, checkString(recordEvent.getSpreadplusminussecondtwo()));
	
			stmt.executeUpdate();  
		    resultSet = stmt.getGeneratedKeys();  
		    resultSet.next();
		    long primaryKey = resultSet.getLong(1);
		    recordEvent.setId(primaryKey);

		    closeStreams(stmt, resultSet);
		} catch (SQLException sqle) {
			LOGGER.error(sqle);
			LOGGER.error(sqle.getMessage());
			try {
				conn.rollback();
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
				throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, "SQL Exception");
			}
			throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, sqle.getMessage());
		} finally {
			try {
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		LOGGER.info("Exiting setSpreadEvent()");
		return recordEvent.getId();
	}

	/**
	 * 
	 * @param recordEvent
	 * @return
	 * @throws BatchException
	 */
	public Long setTotalEvent(TotalRecordEvent recordEvent) throws BatchException {
		LOGGER.info("Entering setTotalEvent()");
		LOGGER.info("TotalRecordEvent: " + recordEvent);
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		try {
			recordEvent.setId(null);
			StringBuffer sb = new StringBuffer(200);
			sb.append("INSERT into totalrecordevents (accountid, amount, attempts, attempttime, ")
					.append("currentattempts, datecreated, datemodified, datentime, eventdatetime, ")
					.append("eventid, eventid1, eventid2, eventname, eventteam1, eventteam2, ")
					.append("eventtype, groupid, iscompleted, sport, userid, wtype, ")
					.append("totalinputfirstone, totalinputfirsttwo, ")
					.append("totalinputjuicefirstone, totalinputjuicefirsttwo, ")
					.append("totalinputjuicesecondone, totalinputjuicesecondtwo, ")
					.append("totalinputsecondone, totalinputsecondtwo, ")
					.append("totaljuiceplusminusfirstone, totaljuiceplusminusfirsttwo, ")
					.append("totaljuiceplusminussecondone, totaljuiceplusminussecondtwo) ")
					.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ")
					.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	
			stmt = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, checkLong(recordEvent.getAccountid()));
			stmt.setString(2, checkString(recordEvent.getAmount()));
			stmt.setInt(3, checkInteger(recordEvent.getAttempts()));
			stmt.setTimestamp(4, new java.sql.Timestamp(checkTimestamp(recordEvent.getAttempttime()).getTime()));
			stmt.setInt(5, checkInteger(recordEvent.getCurrentattempts()));
			stmt.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
			stmt.setTimestamp(7, new java.sql.Timestamp(new Date().getTime()));
			stmt.setTimestamp(8, new java.sql.Timestamp(checkTimestamp(recordEvent.getDatentime()).getTime()));
			stmt.setTimestamp(9, new java.sql.Timestamp(checkTimestamp(recordEvent.getEventdatetime()).getTime()));
			stmt.setInt(10, checkInteger(recordEvent.getEventid()));
			stmt.setInt(11, checkInteger(recordEvent.getEventid1()));
			stmt.setInt(12, checkInteger(recordEvent.getEventid2()));
			stmt.setString(13, checkString(recordEvent.getEventname()));
			stmt.setString(14, checkString(recordEvent.getEventteam1()));
			stmt.setString(15, checkString(recordEvent.getEventteam2()));
			stmt.setString(16, checkString(recordEvent.getEventtype()));
			stmt.setLong(17, checkLong(recordEvent.getGroupid()));
			stmt.setBoolean(18, checkBoolean(recordEvent.getIscompleted()));
			stmt.setString(19, checkString(recordEvent.getSport()));
			stmt.setLong(20, checkLong(recordEvent.getUserid()));
			stmt.setString(21, checkString(recordEvent.getWtype()));
			
			stmt.setString(22, checkString(recordEvent.getTotalinputfirstone()));
			stmt.setString(23, checkString(recordEvent.getTotalinputfirsttwo()));
			stmt.setString(24, checkString(recordEvent.getTotalinputjuicefirstone()));
			stmt.setString(25, checkString(recordEvent.getTotalinputjuicefirsttwo()));
			stmt.setString(26, checkString(recordEvent.getTotalinputjuicesecondone()));
			stmt.setString(27, checkString(recordEvent.getTotalinputjuicesecondtwo()));
			stmt.setString(28, checkString(recordEvent.getTotalinputsecondone()));
			stmt.setString(29, checkString(recordEvent.getTotalinputsecondtwo()));
			stmt.setString(30, checkString(recordEvent.getTotaljuiceplusminusfirstone()));
			stmt.setString(31, checkString(recordEvent.getTotaljuiceplusminusfirsttwo()));
			stmt.setString(32, checkString(recordEvent.getTotaljuiceplusminussecondone()));
			stmt.setString(33, checkString(recordEvent.getTotaljuiceplusminussecondtwo()));
	
			stmt.executeUpdate();  
		    resultSet = stmt.getGeneratedKeys();  
		    resultSet.next();
		    long primaryKey = resultSet.getLong(1);
		    recordEvent.setId(primaryKey);
	
		    closeStreams(stmt, resultSet);
		} catch (SQLException sqle) {
			LOGGER.error(sqle);
			LOGGER.error(sqle.getMessage());
			try {
				conn.rollback();
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
				throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, "SQL Exception");
			}
			throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, sqle.getMessage());
		} finally {
			try {
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		LOGGER.info("Exiting setTotalEvent()");
		return recordEvent.getId();
	}

	/**
	 * 
	 * @param recordEvent
	 * @return
	 * @throws BatchException
	 */
	public Long setMlEvent(MlRecordEvent recordEvent) throws BatchException {
		LOGGER.info("Entering setMlEvent()");
		LOGGER.info("MlRecordEvent: " + recordEvent);
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		try {
			recordEvent.setId(null);
			StringBuffer sb = new StringBuffer(200);
			sb.append("INSERT into mlrecordevents (accountid, amount, attempts, attempttime, ")
					.append("currentattempts, datecreated, datemodified, datentime, eventdatetime, ")
					.append("eventid, eventid1, eventid2, eventname, eventteam1, eventteam2, ")
					.append("eventtype, groupid, iscompleted, sport, userid, wtype, ")
					.append("mlinputfirstone, mlinputfirsttwo, ").append("mlinputsecondone, mlinputsecondtwo, ")
					.append("mlplusminusfirstone, mlplusminusfirsttwo, ")
					.append("mlplusminussecondone, mlplusminussecondtwo) ")
					.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ")
					.append("?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stmt = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, checkLong(recordEvent.getAccountid()));
			stmt.setString(2, checkString(recordEvent.getAmount()));
			stmt.setInt(3, checkInteger(recordEvent.getAttempts()));
			stmt.setTimestamp(4, new java.sql.Timestamp(checkTimestamp(recordEvent.getAttempttime()).getTime()));
			stmt.setInt(5, checkInteger(recordEvent.getCurrentattempts()));
			stmt.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
			stmt.setTimestamp(7, new java.sql.Timestamp(new Date().getTime()));
			stmt.setTimestamp(8, new java.sql.Timestamp(checkTimestamp(recordEvent.getDatentime()).getTime()));
			stmt.setTimestamp(9, new java.sql.Timestamp(checkTimestamp(recordEvent.getEventdatetime()).getTime()));
			stmt.setInt(10, checkInteger(recordEvent.getEventid()));
			stmt.setInt(11, checkInteger(recordEvent.getEventid1()));
			stmt.setInt(12, checkInteger(recordEvent.getEventid2()));
			stmt.setString(13, checkString(recordEvent.getEventname()));
			stmt.setString(14, checkString(recordEvent.getEventteam1()));
			stmt.setString(15, checkString(recordEvent.getEventteam2()));
			stmt.setString(16, checkString(recordEvent.getEventtype()));
			stmt.setLong(17, checkLong(recordEvent.getGroupid()));
			stmt.setBoolean(18, checkBoolean(recordEvent.getIscompleted()));
			stmt.setString(19, checkString(recordEvent.getSport()));
			stmt.setLong(20, checkLong(recordEvent.getUserid()));
			stmt.setString(21, checkString(recordEvent.getWtype()));

			stmt.setString(22, checkString(recordEvent.getMlinputfirstone()));
			stmt.setString(23, checkString(recordEvent.getMlinputfirsttwo()));
			stmt.setString(24, checkString(recordEvent.getMlinputsecondone()));
			stmt.setString(25, checkString(recordEvent.getMlinputsecondtwo()));
			stmt.setString(26, checkString(recordEvent.getMlplusminusfirstone()));
			stmt.setString(27, checkString(recordEvent.getMlplusminusfirsttwo()));
			stmt.setString(28, checkString(recordEvent.getMlplusminussecondone()));
			stmt.setString(29, checkString(recordEvent.getMlplusminussecondtwo()));

			stmt.executeUpdate();
			resultSet = stmt.getGeneratedKeys();
			resultSet.next();
			long primaryKey = resultSet.getLong(1);
			recordEvent.setId(primaryKey);

			closeStreams(stmt, resultSet);
		} catch (SQLException sqle) {
			LOGGER.error(sqle);
			LOGGER.error(sqle.getMessage());
			try {
				conn.rollback();
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
				throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, "SQL Exception");
			}
			throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, sqle.getMessage());
		} finally {
			try {
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		LOGGER.info("Exiting setMlEvent()");
		return recordEvent.getId();
	}

	/**
	 * 
	 * @param spreadid
	 * @return
	 * @throws BatchException
	 */
	public List<AccountEvent> getSpreadActiveAccountEvents(Long spreadid) throws BatchException {
		LOGGER.info("Entering getSpreadActiveAccountEvents()");
		LOGGER.debug("spreadid: " + spreadid);
		final List<AccountEvent> accountEventList = new ArrayList<AccountEvent>();
		Statement stmt = null;
		ResultSet resultSet = null;

		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(
					"SELECT * FROM accountevent where spreadid = " + spreadid + " AND iscompleted = false");

			// Loop through all the results
			while (resultSet.next()) {
				final AccountEvent accountEvent = new AccountEvent();
				accountEvent.setAccountconfirmation(resultSet.getString("accountconfirmation"));
				accountEvent.setAccounthtml(resultSet.getString("accounthtml"));
				accountEvent.setAccountid(resultSet.getLong("accountid"));
				accountEvent.setAmount(resultSet.getString("amount"));
				accountEvent.setActualamount(resultSet.getString("actualamount"));
				accountEvent.setAttempts(resultSet.getInt("attempts"));
				accountEvent.setAttempttime(resultSet.getTimestamp("attempttime"));
				accountEvent.setCurrentattempts(resultSet.getInt("currentattempts"));
				accountEvent.setErrorcode(resultSet.getInt("errorcode"));
				accountEvent.setErrorexception(resultSet.getString("errorexception"));
				accountEvent.setErrormessage(resultSet.getString("errormessage"));
				accountEvent.setEventdatetime(resultSet.getTimestamp("eventdatetime"));
				accountEvent.setEventid(resultSet.getInt("eventid"));
				accountEvent.setEventname(resultSet.getString("eventname"));
				accountEvent.setGroupid(resultSet.getLong("groupid"));
				accountEvent.setId(resultSet.getLong("id"));
				accountEvent.setIscompleted(resultSet.getBoolean("iscompleted"));
				accountEvent.setMaxspreadamount(resultSet.getInt("maxspreadamount"));
				accountEvent.setMaxtotalamount(resultSet.getInt("maxtotalamount"));
				accountEvent.setMaxmlamount(resultSet.getInt("maxmlamount"));
				accountEvent.setMlindicator(resultSet.getString("mlindicator"));
				accountEvent.setTimezone(resultSet.getString("timezone"));
				accountEvent.setMl(resultSet.getFloat("ml"));
				accountEvent.setMlid(resultSet.getLong("mlid"));
				accountEvent.setMljuice(resultSet.getFloat("mljuice"));
				accountEvent.setName(resultSet.getString("name"));
				accountEvent.setOwnerpercentage(resultSet.getInt("ownerpercentage"));
				accountEvent.setPartnerpercentage(resultSet.getInt("partnerpercentage"));
				accountEvent.setProxy(resultSet.getString("proxy"));
				accountEvent.setSport(resultSet.getString("sport"));
				accountEvent.setSpreadindicator(resultSet.getString("spreadindicator"));
				accountEvent.setSpread(resultSet.getFloat("spread"));
				accountEvent.setSpreadid(resultSet.getLong("spreadid"));
				accountEvent.setSpreadjuice(resultSet.getFloat("spreadjuice"));
				accountEvent.setStatus(resultSet.getString("status"));
				accountEvent.setTotal(resultSet.getFloat("total"));
				accountEvent.setTotalid(resultSet.getLong("totalid"));
				accountEvent.setTotalindicator(resultSet.getString("totalindicator"));
				accountEvent.setTotaljuice(resultSet.getFloat("totaljuice"));
				accountEvent.setType(resultSet.getString("type"));
				accountEvent.setUserid(resultSet.getLong("userid"));
				accountEvent.setWagertype(resultSet.getString("wagertype"));

				accountEventList.add(accountEvent);
			}
		} catch (SQLException sqle) {
			LOGGER.error(sqle);
			LOGGER.error(sqle.getMessage());
			try {
				conn.rollback();
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
				throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, "SQL Exception");
			}
			throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, sqle.getMessage());
		} finally {
			try {
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		LOGGER.info("Exiting getSpreadActiveAccountEvents()");
		return accountEventList;
	}

	/**
	 * 
	 * @param totalid
	 * @return
	 * @throws BatchException
	 */
	public List<AccountEvent> getTotalActiveAccountEvents(Long totalid) throws BatchException {
		LOGGER.info("Entering getTotalActiveAccountEvents()");
		LOGGER.debug("totalid: " + totalid);
		List<AccountEvent> accountEventList = new ArrayList<AccountEvent>();
		Statement stmt = null;
		ResultSet resultSet = null;

		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery("SELECT * FROM accountevent where totalid = " + totalid + " AND iscompleted = false");

			while (resultSet.next()) {
				final AccountEvent accountEvent = new AccountEvent();
				accountEvent.setAccountconfirmation(resultSet.getString("accountconfirmation"));
				accountEvent.setAccounthtml(resultSet.getString("accounthtml"));
				accountEvent.setAccountid(resultSet.getLong("accountid"));
				accountEvent.setAmount(resultSet.getString("amount"));
				accountEvent.setActualamount(resultSet.getString("actualamount"));
				accountEvent.setAttempts(resultSet.getInt("attempts"));
				accountEvent.setAttempttime(resultSet.getTimestamp("attempttime"));
				accountEvent.setCurrentattempts(resultSet.getInt("currentattempts"));
				accountEvent.setErrorcode(resultSet.getInt("errorcode"));
				accountEvent.setErrorexception(resultSet.getString("errorexception"));
				accountEvent.setErrormessage(resultSet.getString("errormessage"));
				accountEvent.setEventdatetime(resultSet.getTimestamp("eventdatetime"));
				accountEvent.setEventid(resultSet.getInt("eventid"));
				accountEvent.setEventname(resultSet.getString("eventname"));
				accountEvent.setGroupid(resultSet.getLong("groupid"));
				accountEvent.setId(resultSet.getLong("id"));
				accountEvent.setIscompleted(resultSet.getBoolean("iscompleted"));
				accountEvent.setMaxspreadamount(resultSet.getInt("maxspreadamount"));
				accountEvent.setMaxtotalamount(resultSet.getInt("maxtotalamount"));
				accountEvent.setMaxmlamount(resultSet.getInt("maxmlamount"));
				accountEvent.setMlindicator(resultSet.getString("mlindicator"));
				accountEvent.setTimezone(resultSet.getString("timezone"));
				accountEvent.setMl(resultSet.getFloat("ml"));
				accountEvent.setMlid(resultSet.getLong("mlid"));
				accountEvent.setMljuice(resultSet.getFloat("mljuice"));
				accountEvent.setName(resultSet.getString("name"));
				accountEvent.setOwnerpercentage(resultSet.getInt("ownerpercentage"));
				accountEvent.setPartnerpercentage(resultSet.getInt("partnerpercentage"));
				accountEvent.setProxy(resultSet.getString("proxy"));
				accountEvent.setSport(resultSet.getString("sport"));
				accountEvent.setSpreadindicator(resultSet.getString("spreadindicator"));
				accountEvent.setSpread(resultSet.getFloat("spread"));
				accountEvent.setSpreadid(resultSet.getLong("spreadid"));
				accountEvent.setSpreadjuice(resultSet.getFloat("spreadjuice"));
				accountEvent.setStatus(resultSet.getString("status"));
				accountEvent.setTotal(resultSet.getFloat("total"));
				accountEvent.setTotalid(resultSet.getLong("totalid"));
				accountEvent.setTotalindicator(resultSet.getString("totalindicator"));
				accountEvent.setTotaljuice(resultSet.getFloat("totaljuice"));
				accountEvent.setType(resultSet.getString("type"));
				accountEvent.setUserid(resultSet.getLong("userid"));
				accountEvent.setWagertype(resultSet.getString("wagertype"));

				accountEventList.add(accountEvent);
			}
		} catch (SQLException sqle) {
			LOGGER.error(sqle);
			LOGGER.error(sqle.getMessage());
			try {
				conn.rollback();
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
				throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, "SQL Exception");
			}
			throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, sqle.getMessage());
		} finally {
			try {
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		LOGGER.info("Exiting getTotalActiveAccountEvents()");
		return accountEventList;
	}

	/**
	 * 
	 * @param mlid
	 * @return
	 * @throws SQLException
	 */
	public List<AccountEvent> getMlActiveAccountEvents(Long mlid) throws BatchException {
		LOGGER.info("Entering getMlActiveAccountEvents()");
		LOGGER.debug("mlid: " + mlid);
		List<AccountEvent> accountEventList = new ArrayList<AccountEvent>();
		Statement stmt = null;
		ResultSet resultSet = null;

		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery("SELECT * FROM accountevent where mlid = " + mlid + " AND iscompleted = false");

			while (resultSet.next()) {
				final AccountEvent accountEvent = new AccountEvent();

				accountEvent.setAccountconfirmation(resultSet.getString("accountconfirmation"));
				accountEvent.setAccounthtml(resultSet.getString("accounthtml"));
				accountEvent.setAccountid(resultSet.getLong("accountid"));
				accountEvent.setAmount(resultSet.getString("amount"));
				accountEvent.setActualamount(resultSet.getString("actualamount"));
				accountEvent.setAttempts(resultSet.getInt("attempts"));
				accountEvent.setAttempttime(resultSet.getTimestamp("attempttime"));
				accountEvent.setCurrentattempts(resultSet.getInt("currentattempts"));
				accountEvent.setErrorcode(resultSet.getInt("errorcode"));
				accountEvent.setErrorexception(resultSet.getString("errorexception"));
				accountEvent.setErrormessage(resultSet.getString("errormessage"));
				LOGGER.debug("resultSet.getTimestamp()): " + resultSet.getTimestamp("eventdatetime"));
				accountEvent.setEventdatetime(resultSet.getTimestamp("eventdatetime"));
				accountEvent.setEventid(resultSet.getInt("eventid"));
				accountEvent.setEventname(resultSet.getString("eventname"));
				accountEvent.setGroupid(resultSet.getLong("groupid"));
				accountEvent.setId(resultSet.getLong("id"));
				accountEvent.setIscompleted(resultSet.getBoolean("iscompleted"));
				accountEvent.setMaxspreadamount(resultSet.getInt("maxspreadamount"));
				accountEvent.setMaxtotalamount(resultSet.getInt("maxtotalamount"));
				accountEvent.setMaxmlamount(resultSet.getInt("maxmlamount"));
				accountEvent.setMlindicator(resultSet.getString("mlindicator"));
				accountEvent.setTimezone(resultSet.getString("timezone"));
				accountEvent.setMl(resultSet.getFloat("ml"));
				accountEvent.setMlid(resultSet.getLong("mlid"));
				accountEvent.setMljuice(resultSet.getFloat("mljuice"));
				accountEvent.setName(resultSet.getString("name"));
				accountEvent.setOwnerpercentage(resultSet.getInt("ownerpercentage"));
				accountEvent.setPartnerpercentage(resultSet.getInt("partnerpercentage"));
				accountEvent.setProxy(resultSet.getString("proxy"));
				accountEvent.setSport(resultSet.getString("sport"));
				accountEvent.setSpreadindicator(resultSet.getString("spreadindicator"));
				accountEvent.setSpread(resultSet.getFloat("spread"));
				accountEvent.setSpreadid(resultSet.getLong("spreadid"));
				accountEvent.setSpreadjuice(resultSet.getFloat("spreadjuice"));
				accountEvent.setStatus(resultSet.getString("status"));
				accountEvent.setTotal(resultSet.getFloat("total"));
				accountEvent.setTotalid(resultSet.getLong("totalid"));
				accountEvent.setTotalindicator(resultSet.getString("totalindicator"));
				accountEvent.setTotaljuice(resultSet.getFloat("totaljuice"));
				accountEvent.setType(resultSet.getString("type"));
				accountEvent.setUserid(resultSet.getLong("userid"));
				accountEvent.setWagertype(resultSet.getString("wagertype"));

				accountEventList.add(accountEvent);
			}
		} catch (SQLException sqle) {
			LOGGER.error(sqle);
			LOGGER.error(sqle.getMessage());
			try {
				conn.rollback();
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
				throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, "SQL Exception");
			}
			throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, sqle.getMessage());
		} finally {
			try {
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		LOGGER.info("Exiting getMlActiveAccountEvents()");
		return accountEventList;
	}

	/**
	 * 
	 * @param accountEvent
	 * @throws BatchException
	 */
	public void setupAccountEvent(AccountEvent accountEvent) throws BatchException {
		LOGGER.info("Entering setupAccountEvent()");
		LOGGER.debug("AccountEvent: " + accountEvent);
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		try {
			final StringBuffer sb = new StringBuffer(1000);
			final Date dateModified = new java.util.Date();
			sb.append("INSERT into accountevent (accountid, amount, actualamount, attempts, ")
					.append("datecreated, datemodified, eventdatetime, ")
					.append("eventid, eventname, ")
					.append("groupid, iscompleted, ")
					.append("maxspreadamount, maxtotalamount, maxmlamount, ")
					.append("mlindicator, timezone, ml, mlid, mljuice, ")
					.append("name, ownerpercentage, partnerpercentage, ")
					.append("proxy, sport, ")
					.append("spreadindicator, spread, spreadid, spreadjuice, status, ")
					.append("total, totalid, totalindicator, totaljuice, ")
					.append("type, userid, wagertype) ")
					.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ")
					.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stmt = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, checkLong(accountEvent.getAccountid()));
			stmt.setString(2, checkString(accountEvent.getAmount()));
			stmt.setString(3, checkString(accountEvent.getActualamount()));
			stmt.setInt(4, checkInteger(accountEvent.getAttempts()));
			stmt.setTimestamp(5, new java.sql.Timestamp(checkTimestamp(dateModified).getTime()));
			stmt.setTimestamp(6, new java.sql.Timestamp(checkTimestamp(dateModified).getTime()));
			stmt.setTimestamp(7, new java.sql.Timestamp(checkTimestamp(accountEvent.getEventdatetime()).getTime()));
			stmt.setInt(8, checkInteger(accountEvent.getEventid()));
			stmt.setString(9, checkString(accountEvent.getEventname()));
			stmt.setLong(10, checkLong(accountEvent.getGroupid()));
			stmt.setBoolean(11, checkBoolean(accountEvent.getIscompleted()));
			stmt.setInt(12, checkInteger(accountEvent.getMaxspreadamount()));
			stmt.setInt(13, checkInteger(accountEvent.getMaxtotalamount()));
			stmt.setInt(14, checkInteger(accountEvent.getMaxmlamount()));
			stmt.setString(15, checkString(accountEvent.getMlindicator()));
			stmt.setString(16, checkString(accountEvent.getTimezone()));
			stmt.setFloat(17, checkFloat(accountEvent.getMl()));
			stmt.setLong(18, checkLong(accountEvent.getMlid()));
			stmt.setFloat(19, checkFloat(accountEvent.getMljuice()));
			stmt.setString(20, checkString(accountEvent.getName()));
			stmt.setInt(21, checkInteger(accountEvent.getOwnerpercentage()));
			stmt.setInt(22, checkInteger(accountEvent.getPartnerpercentage()));
			stmt.setString(23, checkString(accountEvent.getProxy()));
			stmt.setString(24, checkString(accountEvent.getSport()));
			stmt.setString(25, checkString(accountEvent.getSpreadindicator()));
			stmt.setFloat(26, checkFloat(accountEvent.getSpread()));
			stmt.setLong(27, checkLong(accountEvent.getSpreadid()));
			stmt.setFloat(28, checkFloat(accountEvent.getSpreadjuice()));
			stmt.setString(29, checkString(accountEvent.getStatus()));
			stmt.setFloat(30, checkFloat(accountEvent.getTotal()));
			stmt.setFloat(31, checkLong(accountEvent.getTotalid()));
			stmt.setString(32, checkString(accountEvent.getTotalindicator()));
			stmt.setFloat(33, checkFloat(accountEvent.getTotaljuice()));
			stmt.setString(34, checkString(accountEvent.getType()));
			stmt.setLong(35, checkLong(accountEvent.getUserid()));
			stmt.setString(36, checkString(accountEvent.getWagertype()));

			// Run the update query
			stmt.executeUpdate();
			resultSet = stmt.getGeneratedKeys();
			resultSet.next();
			long primaryKey = resultSet.getLong(1);
			accountEvent.setId(primaryKey);

			closeStreams(stmt, resultSet);			
		} catch (SQLException sqle) {
			LOGGER.error(sqle);
			LOGGER.error(sqle.getMessage());
			try {
				conn.rollback();
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
				throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
						BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, "SQL Exception");
			}
			throw new BatchException(BatchErrorCodes.UNEXPECTED_APPLICATION_EXCEPTION,  
					BatchErrorMessage.UNEXPECTED_APPLICATION_EXCEPTION, sqle.getMessage());
		} finally {
			try {
				closeStreams(stmt, resultSet);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		LOGGER.info("Exiting setupAccountEvent()");		
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private String checkString(String value) {
		if (value == null) {
			value = "";
		}
		return value;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private Long checkLong(Long value) {
		if (value == null) {
			value = new Long(0);
		}
		return value;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private Integer checkInteger(Integer value) {
		if (value == null) {
			value = new Integer(0);
		}
		return value;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private Float checkFloat(Float value) {
		if (value == null) {
			value = new Float(0);
		}
		return value;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private Date checkTimestamp(Date value) {
		if (value == null) {
			value = new Date();
		}
		return value;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private Boolean checkBoolean(Boolean value) {
		if (value == null) {
			value = new Boolean(false);
		}
		return value;
	}	
}