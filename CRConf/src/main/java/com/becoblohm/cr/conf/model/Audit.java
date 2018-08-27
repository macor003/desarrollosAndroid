package com.becoblohm.cr.conf.model;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.becoblohm.cr.conf.Global;

public class Audit {

	private int processId;

	protected String storeNumber;

	private String posNumber;

	private String posType;

	private String process;

	private int cashierId;

	private int authId;

	private Date date;

	private Date time;

	private Long tableId;

	private String table;

	private String transactionType;

	private String authType;

	private String classe;

	private String description;

	private String logType;

	private static Audit auditModel;

	private Audit() {

	}

	public static Audit getAuditInstance() {
		if (auditModel == null) {
			auditModel = new Audit();
		}
		auditModel.setAuthId(0);
		auditModel.setAuthType("");
		auditModel.setCashierId(Global.getCurrentSession().getCashier().getIdEPA());
		auditModel.setClasse("");
		auditModel.setDate(null);
		auditModel.setDescription("");
		auditModel.setLogType(LogType.INFO.getValue());
		auditModel.setPosNumber(Global.getPosId());
		auditModel.setPosType(Global.getPosType());
		auditModel.setProcess("");
		auditModel.setProcessId(0);
		auditModel.setStoreNumber(Global.getStore());
		auditModel.setTable("");
		auditModel.setTime(null);
		auditModel.setTransactionType("");

		return auditModel;
	}

	public int getProcessId() {
		return processId;
	}

	public String getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getPosNumber() {
		return posNumber;
	}

	public void setPosNumber(String posNumber) {
		this.posNumber = posNumber;
	}

	public String getPosType() {
		return posType;
	}

	public void setPosType(String posType) {
		this.posType = posType;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public int getCashierId() {
		return cashierId;
	}

	public void setCashierId(int cashierId) {
		this.cashierId = cashierId;
	}

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public enum LogType {
		ERROR("ERROR"), WARNING("WARNING"), INFO("INFO");

		private static final Map<String, LogType> lookup = new HashMap<String, LogType>();

		static {
			for (LogType s : EnumSet.allOf(LogType.class))
				lookup.put(s.getValue(), s);
		}

		private String value;

		LogType(String value) {
			this.setValue(value);
		}

		public static LogType get(String value) {
			return lookup.get(value);
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum SourceTransactionType {
		Sale("VENTA"), Cancellation("ANULACION"), Refund("DEVOLUCION"), CancellationOrder("ANULAORDENDEVENTA"), Order(
				"ORDENDEVENTA"), CreditsEpaPay("PAGOCREDITO"), CreditsEpaCancellation("ANULACREDITO"), Credits(
						"ACREENCIAS"), NonAttendance("NOPRESENCIAL"), OrderDeposit("ORDENDEVENTA"), CancellationCredits(
								"ANULACIONACREENCIA"), RefundCredits("DEVOLUCIONACREENCIA");

		private static final Map<String, SourceTransactionType> lookup = new HashMap<String, SourceTransactionType>();

		static {
			for (SourceTransactionType s : EnumSet.allOf(SourceTransactionType.class))
				lookup.put(s.getValue(), s);
		}

		private String value;

		SourceTransactionType(String value) {
			this.setValue(value);
		}

		public static SourceTransactionType get(String value) {
			return lookup.get(value);
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum AuthorizationType {
		Manual("MANUAL"), Finger("HUELLA");

		private static final Map<String, AuthorizationType> lookup = new HashMap<String, AuthorizationType>();

		static {
			for (AuthorizationType s : EnumSet.allOf(AuthorizationType.class))
				lookup.put(s.getValue(), s);
		}

		private String value;

		AuthorizationType(String value) {
			this.setValue(value);
		}

		public static AuthorizationType get(String value) {
			return lookup.get(value);
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
