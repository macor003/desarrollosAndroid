/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package query.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.becoblohm.cr.crjpa.controller.FormadepagopuntoagilJpaController;
import com.becoblohm.cr.crjpa.controller.SesionJpaController;
import com.becoblohm.cr.crjpa.controller.TipodocumentoJpaController;
import com.becoblohm.cr.crjpa.controller.TransaccionDocumentoJpaController;
import com.becoblohm.cr.crjpa.controller.TransaccionJpaController;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.becoblohm.cr.models.TransactionDocument;
import com.becoblohm.cr.models.VPosAdditionalData;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Formadepago;
import crjpa.FormadepagoJpaController;
import crjpa.Tipodocumento;
import crjpa.Transaccion;
import crjpa.Transaccionarticulo;
import crjpa.Transacciondocumento;

public class TransaccionQueryTest {

	private long startTime;
	private long endTime;
	private EntityManagerFactory emf;
	private HashMap<String, TableHolder> jpaEntities;
	private HashMap<String, TableHolder> jpaTables;
	private ArrayList<TableHolder> dbTables;

	@Test
	public void testquery() throws Exception {

		dbTables = new ArrayList<TableHolder>();
		jpaTables = new HashMap<String, TransaccionQueryTest.TableHolder>();
		jpaEntities = new HashMap<String, TransaccionQueryTest.TableHolder>();

		EntityManager em = emf.createEntityManager();

		Query q = em.createNativeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='CRPOSV2'");
		List tmp = q.getResultList();
		TableHolder table;
		for (Object obj : tmp) {
			table = new TableHolder(obj.toString().toLowerCase());
			dbTables.add(table);
			q = em.createNativeQuery("SELECT column_name FROM information_schema.columns WHERE table_schema='CRPOSV2' AND table_name='"
					+ obj.toString() + "'");
			List tmp2 = q.getResultList();
			for (Object obj2 : tmp2) {
				table.addColumn(obj2.toString().toLowerCase());
			}
			q = em.createNativeQuery("SELECT referenced_table_name FROM information_schema.referential_constraints WHERE constraint_schema='CRPOSV2' AND table_name='"
					+ obj.toString() + "'");
			List tmp3 = q.getResultList();
			for (Object obj3 : tmp3) {
				table.addReferencedTable(obj3.toString().toLowerCase());
			}
		}

		Metamodel model = em.getMetamodel();
		Set<EntityType<?>> entities = model.getEntities();
		TableHolder jpaTable;
		TableHolder joinTable;
		for (EntityType entity : entities) {
			Class entityClass = Class.forName(entity.getJavaType().getName());
			javax.persistence.Table anotTmp = (Table) entityClass.getAnnotation(javax.persistence.Table.class);
			jpaTable = new TableHolder(anotTmp.name());
			javax.persistence.Column columnAnot;
			javax.persistence.JoinColumn joinAnot;
			javax.persistence.JoinTable joinTableAnot;
			for (Field f : entityClass.getDeclaredFields()) {
				if (f.getAnnotation(javax.persistence.Column.class) != null) {
					columnAnot = f.getAnnotation(javax.persistence.Column.class);
					jpaTable.addColumn(columnAnot.name().toLowerCase());
				} else if (f.getAnnotation(javax.persistence.ManyToOne.class) != null) {
					joinAnot = f.getAnnotation(javax.persistence.JoinColumn.class);
					jpaTable.addColumn(joinAnot.name());
					jpaTable.addReferencedTable(f.getType().getSimpleName());
				} else if (f.getAnnotation(javax.persistence.JoinTable.class) != null) {
					joinTableAnot = f.getAnnotation(javax.persistence.JoinTable.class);
					joinTable = new TableHolder(joinTableAnot.name());
					for (javax.persistence.JoinColumn column : joinTableAnot.joinColumns()) {
						joinTable.addColumn(column.name());
						joinTable.addReferencedTable(entity.getName());
					}
					for (javax.persistence.JoinColumn column : joinTableAnot.inverseJoinColumns()) {
						joinTable.addColumn(column.name());
						if (f.getGenericType() instanceof ParameterizedType) {
							joinTable.addReferencedTable(((Class) ((ParameterizedType) f.getGenericType())
									.getActualTypeArguments()[0]).getSimpleName());
						}
						joinTable.addReferencedTable(f.getGenericType().getClass().getSimpleName());
					}
					jpaTables.put(joinTable.getTableName(), joinTable);
				} else if (f.getAnnotation(javax.persistence.EmbeddedId.class) != null) {
					for (Field idfield : f.getType().getDeclaredFields()) {
						for (Annotation idAnot : idfield.getAnnotations()) {
							if (idAnot instanceof javax.persistence.Column) {
								jpaTable.addColumn(((javax.persistence.Column) idAnot).name().toLowerCase());
							}
						}
					}
				}
			}
			jpaTables.put(jpaTable.getTableName(), jpaTable);
			jpaEntities.put(entity.getName(), jpaTable);
		}
		System.out.println(compareSchemas());
	}

	private boolean compareSchemas() {
		boolean schemasAreEqual = true;
		TableHolder jpaTable;
		boolean isReferenced;
		for (TableHolder dbTable : dbTables) {
			if (jpaTables.containsKey(dbTable.getTableName())) {
				jpaTable = jpaTables.get(dbTable.getTableName());
				for (String dbcolumn : dbTable.getColumns()) {
					if (!jpaTable.getColumns().contains(dbcolumn)) {
						// System.err.println("no existe columna "+dbcolumn
						// +" de tabla "+dbTable.getTableName()
						// +" en tabla "+jpaTable.getTableName());
						schemasAreEqual = false;
						break;
					}
				}
				for (String referencedTable : dbTable.getReferencedTables()) {
					isReferenced = false;
					for (String jpaRefTable : jpaTable.getReferencedTables()) {
						if (jpaEntities.containsKey(jpaRefTable)
								&& jpaEntities.get(jpaRefTable).getTableName().equalsIgnoreCase(referencedTable)) {
							isReferenced = true;
							break;
						}
					}
					if (!isReferenced) {
						schemasAreEqual = false;
						break;
					}
				}
				if (!schemasAreEqual) {
					break;
				}
			} else {
				schemasAreEqual = false;
				break;
			}
		}
		return schemasAreEqual;
	}

	private class TableHolder {
		private String tableName;
		private ArrayList<String> columns;
		private ArrayList<String> referencedTables;

		public TableHolder(String tableName) {
			this.tableName = tableName;
			this.columns = new ArrayList<String>();
			this.referencedTables = new ArrayList<String>();
		}

		public void addColumn(String column) {
			this.columns.add(column);
		}

		public ArrayList<String> getColumns() {
			return this.columns;
		}

		public ArrayList<String> getReferencedTables() {
			return referencedTables;
		}

		public void addReferencedTable(String referencedTable) {
			this.referencedTables.add(referencedTable);
		}

		public String getTableName() {
			return tableName;
		}

	}

	@Test
	public void testCreateZMensualReport() throws Exception {

		Date initialDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(initialDate);
		cal.set(Calendar.DATE, 01);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 1);
		initialDate = cal.getTime();

		Date finalDate = new Date();
		Calendar cal2 = Calendar.getInstance();
		// cal2.setTime(finalDate);
		// cal2.add(Calendar.DATE,28);
		// cal2.set(Calendar.HOUR_OF_DAY, 23);
		// cal2.set(Calendar.MINUTE, 59);
		// cal2.set(Calendar.SECOND, 59);
		// cal2.set(Calendar.MILLISECOND, 59);
		finalDate = cal2.getTime();

		// System.out.println("fecha inicial:"+initialDate+
		// " fecha final "+finalDate);

		CRBigDecimal totalGravado = new CRBigDecimal(BigDecimal.ZERO.doubleValue());
		CRBigDecimal totalExento = new CRBigDecimal(BigDecimal.ZERO.doubleValue());
		CRBigDecimal totalIva = new CRBigDecimal(BigDecimal.ZERO.doubleValue());

		List<Transaccion> tmp = new ArrayList<Transaccion>();
		Query query = emf
				.createEntityManager()
				.createQuery(
						"SELECT t FROM Transaccion t WHERE t.fecha >= :fechaInicial AND t.fecha <= :fechaFinal AND t.estado = 'F'");
		query.setParameter("fechaInicial", initialDate);
		query.setParameter("fechaFinal", finalDate);
		tmp = query.getResultList();

		TreeMap<String, DocumentsTotalsContainer> docs = new TreeMap<String, DocumentsTotalsContainer>();

		System.out.println("fechaInicial:" + initialDate + " fechaFinal: " + finalDate);

		TipodocumentoJpaController jpaController = new TipodocumentoJpaController(this.emf);
		List<Tipodocumento> tipoDoc = jpaController.findTipodocumentoEntitiesJPA();

		for (Iterator iterator = tipoDoc.iterator(); iterator.hasNext();) {
			Tipodocumento tipodoc = (Tipodocumento) iterator.next();
			if (tipodoc.getEssoporteventa().compareToIgnoreCase("S") == 0
					|| tipodoc.getIdContrapartida().getEssoporteventa().compareToIgnoreCase("S") == 0) {
				System.out.println("Agregando documento: " + tipodoc.getNombre());
				DocumentsTotalsContainer tmpContainer = new DocumentsTotalsContainer();
				docs.put(tipodoc.getNombre(), tmpContainer);
			}
		}

		for (Transaccion transTmp : tmp) {

			System.out.println("trans: " + transTmp.getNumero() + " Monto: " + transTmp.getTotalBase() + "+"
					+ transTmp.getTotalImpuesto());

			List<Transaccionarticulo> art = transTmp.getTransaccionarticuloList();
			ArrayList<Transaccionarticulo> articles = new ArrayList<Transaccionarticulo>();
			CRBigDecimal totalGrav = new CRBigDecimal(BigDecimal.ZERO.doubleValue());
			CRBigDecimal totalImp = new CRBigDecimal(BigInteger.ZERO.doubleValue());
			CRBigDecimal totalExent = new CRBigDecimal(BigDecimal.ZERO.doubleValue());
			for (Transaccionarticulo tmpArt : art) {
				articles.add(tmpArt);
				if (tmpArt.getMontoImpuesto().compareTo(BigDecimal.ZERO) > 0) {
					totalGrav = totalGrav.add(new CRBigDecimal(tmpArt.getMontoBase().doubleValue()));
					totalImp = totalImp.add(new CRBigDecimal(tmpArt.getMontoImpuesto().doubleValue()));
				} else {
					totalExent = totalExent.add(new CRBigDecimal(tmpArt.getMontoBase().doubleValue()));
				}
			}

			for (Iterator iterator = transTmp.getTransacciondocumentoList().iterator(); iterator.hasNext();) {
				Transacciondocumento doc = (Transacciondocumento) iterator.next();

				if (doc.getIdTipodocumento().getEssoporteventa().compareToIgnoreCase("S") == 0
						|| doc.getIdTipodocumento().getIdContrapartida().getEssoporteventa().compareToIgnoreCase("S") == 0) {

					System.out.println("Doc: " + doc.getIdTipodocumento().getNombre());

					if (docs.containsKey(doc.getIdTipodocumento().getNombre())) {

						Tipodocumento currentDoc = doc.getIdTipodocumento();
						DocumentsTotalsContainer container = docs.get(doc.getIdTipodocumento().getNombre());
						if (container.getFromNumber() == 0) {
							container.setFromNumber(transTmp.getNumero());
						}
						if (transTmp.getNumero() < container.getFromNumber()) {

							container.setFromNumber(transTmp.getNumero());

						}

						if (transTmp.getNumero() > container.getToNumber()) {
							container.setToNumber(transTmp.getNumero());

						}

						if (transTmp.getTipo().equals(SourceTransactionType.Cancellation.getValue())
								|| transTmp.getTipo().equals(SourceTransactionType.Refund.getValue())) {
							totalGrav = totalGrav.negate();
							totalImp = totalImp.negate();
							totalExent = totalExent.negate();
						}

						if (currentDoc.getMuestraImpuesto().compareToIgnoreCase("S") == 0) {

							container.setTotalGravado(container.getTotalGravado().add(totalGrav.add(totalImp))); // mas
																													// imp

						} else {
							container.setTotalGravado(container.getTotalGravado().add(totalGrav));
							container.setTotalImpuesto(container.getTotalImpuesto().add(totalImp));
						}

						container.setTotalExento(container.getTotalExento().add(totalExent));

						docs.put(doc.getIdTipodocumento().getNombre(), container);
					}
				}
			}
		}

		ArrayList<String> reportLines = new ArrayList<String>();

		String separator = "-------------------------------------------------------";
		reportLines.add(separator);
		for (Iterator iterator = docs.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			DocumentsTotalsContainer details = docs.get(key);
			reportLines.add(key + "              Desde: " + details.getFromNumber() + " Hasta: "
					+ details.getToNumber());
		}
		reportLines.add(separator);
		// reportLines.add(" CAJA:"+sesion.getPosId()+"   TIENDA:"+sesion.getStoreId());
		// reportLines.add(" FECHA:"+sesion.getDateTime()+
		// "   HORA:"+sesion.getDateTime());
		// reportLines.add(printerSerial);
		reportLines.add(separator);
		reportLines.add(" TOTAL DE VENTAS GRAVADAS ");
		reportLines.add(separator);
		for (Iterator iterator = docs.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			DocumentsTotalsContainer details = docs.get(key);
			reportLines.add(key + "                 " + details.getTotalGravado());
		}
		reportLines.add(separator);
		reportLines.add(" TOTAL DE VENTAS EXENTAS ");
		reportLines.add(separator);
		for (Iterator iterator = docs.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			DocumentsTotalsContainer details = docs.get(key);
			reportLines.add(key + "                " + details.getTotalExento());
		}

		reportLines.add(separator);
		reportLines.add(" TOTAL DE IVA ");
		reportLines.add(separator);
		for (Iterator iterator = docs.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			DocumentsTotalsContainer details = docs.get(key);
			reportLines.add(key + "       " + details.getTotalImpuesto());
		}

		for (Iterator iterator = reportLines.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}

	}

	@Test
	public void testquery2() throws Exception {

		SesionJpaController jpacontroller = new SesionJpaController(emf);
		Session lastZReportSession = jpacontroller.findLastZReportDate();
		if (lastZReportSession != null) {
			System.out.println("fecha ultimo reportez: " + lastZReportSession.getzReport_printDate());
			// lastZReportDate = lastZReportSession.getzReport_printDate();
		}

		// System.out.println("resultado"+
		// jpa.findLastZReportDate().getzReport_printDate());
	}

	@Test
	public void testquery3() throws Exception {

		TransaccionJpaController tr = new TransaccionJpaController(emf);

		Transaction tmp = tr.findLastTransactionToReprint();
		if (tmp != null) {
			System.out.println("trans: " + tmp.getId());
		} else {
			System.out.println("null");
		}

	}

	@Test
	public void testquery666() throws Exception {
		//
		// TransaccionJpaController tr = new TransaccionJpaController(emf);
		// Transaction tmp = tr.findTransactionByStore_Pos_Id_Date("22",
		// "159688",
		// "'" + SourceTransactionType.Sale.getValue() + "' , '" +
		// SourceTransactionType.NonAttendance.getValue()
		// + "'", new Date());
		//
		// if (tmp != null) {
		// System.out.println("trans: " + tmp.getId());
		// } else {
		// System.out.println("null");
		// }

	}

	@Test
	public void testquery4() throws Exception {

		com.becoblohm.cr.crjpa.controller.FormadepagoJpaController jpatmp = new com.becoblohm.cr.crjpa.controller.FormadepagoJpaController(
				emf);

		jpatmp.getPaymentMethodList();

		FormadepagoJpaController jpa = new FormadepagoJpaController(emf);

		List<Formadepago> tmp = jpa.findFormadepagoEntities();

		if (tmp.size() > 0) {

			System.out.println("resultado" + tmp.size());
			System.out.println("resultado" + tmp.iterator().next().getFormadepagomonedaList().size());

		}
	}

	@Test
	public void testquery5() throws Exception {

		TransaccionDocumentoJpaController jpacontroller = new TransaccionDocumentoJpaController(emf);
		ArrayList<TransactionDocument> list = jpacontroller.searchLastTransactionDocumentByClientId(new Long(9),
				"17892041");
		if (list != null) {
			for (TransactionDocument transactionDocument : list) {
				System.out.println("Documento: " + transactionDocument.getDocumentText());
			}

			// lastZReportDate = lastZReportSession.getzReport_printDate();
		}

		// System.out.println("resultado"+
		// jpa.findLastZReportDate().getzReport_printDate());
	}

	@Test
	public void testquery6() throws Exception {

		FormadepagopuntoagilJpaController jpa = new FormadepagopuntoagilJpaController(emf);

		jpa.findByTransactionPaymentId(419);

		// System.out.println("resultado"+
		// jpa.findLastZReportDate().getzReport_printDate());
	}

	@Test
	public void testquery06() throws Exception {
		TransaccionJpaController jpa = new TransaccionJpaController(emf);

		Transaction tr = jpa.findTransactionByStore_Pos_Id_Date("26", "164", SourceTransactionType.Sale.getValue(),
				new Date());

		System.out.println(tr.getId());
		System.out.println(tr.getClient().getIdNumber());

		System.out.println(jpa.isTransactionCanceled(tr));
	}

	@Before
	public void setUp() throws Exception {
		// add additional set up code here
		// Global.setCurrentSession(new Session());
		emf = Persistence.createEntityManagerFactory("CRJPAPU");
		startTime = System.nanoTime();

	}

	/**
	 * Perform post-test clean-up.
	 * 
	 * 
	 * 
	 * @generatedBy CodePro at 14/04/11 10:19 AM
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 */
	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here

		endTime = System.nanoTime();
		double elapsedTime = (endTime - startTime);
		double seconds = (double) elapsedTime / 1000000000.0;
		System.out.println("tiempo de prueba " + seconds);
	}

	/**
	 * Launch the test.
	 * 
	 * @param args
	 *            the command line arguments
	 * 
	 * @generatedBy CodePro at 14/04/11 10:19 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TransaccionQueryTest.class);
	}

	private class DetailsContainer {
		private CRBigDecimal positive = CRBigDecimal.ZERO;
		private CRBigDecimal negative = CRBigDecimal.ZERO;

		public CRBigDecimal getPositive() {
			return positive;
		}

		public void setPositive(CRBigDecimal positive) {
			this.positive = this.positive.add(positive);
		}

		public CRBigDecimal getNegative() {
			return negative.negate();
		}

		public void setNegative(CRBigDecimal negative) {
			this.negative = this.negative.add(negative);
		}
	}

	private class DocumentsTotalsContainer {

		private Long fromNumber;
		private Long toNumber;
		private CRBigDecimal totalGravado;
		private CRBigDecimal totalExento;
		private CRBigDecimal totalImpuesto;
		private CRBigDecimal totalIVAPercibido;
		private CRBigDecimal totalDiario;
		private boolean showsTax;
		private boolean saleSupport;
		private boolean counterpart;

		public DocumentsTotalsContainer() {
			this.fromNumber = (long) 0;
			this.toNumber = (long) 0;
			this.totalGravado = CRBigDecimal.ZERO;
			this.totalExento = CRBigDecimal.ZERO;
			this.totalImpuesto = CRBigDecimal.ZERO;
			this.totalIVAPercibido = CRBigDecimal.ZERO;
			this.totalDiario = CRBigDecimal.ZERO;
		}

		public Long getFromNumber() {
			return fromNumber;
		}

		public void setFromNumber(Long fromNumber) {
			this.fromNumber = fromNumber;
		}

		public Long getToNumber() {
			return toNumber;
		}

		public void setToNumber(Long toNumber) {
			this.toNumber = toNumber;
		}

		public CRBigDecimal getTotalGravado() {
			return totalGravado;
		}

		public void setTotalGravado(CRBigDecimal totalGravado) {
			this.totalGravado = totalGravado;
		}

		public CRBigDecimal getTotalExento() {
			return totalExento;
		}

		public void setTotalExento(CRBigDecimal totalExento) {
			this.totalExento = totalExento;
		}

		public CRBigDecimal getTotalImpuesto() {
			return totalImpuesto;
		}

		public void setTotalImpuesto(CRBigDecimal totalImpuesto) {
			this.totalImpuesto = totalImpuesto;
		}

		public CRBigDecimal getTotalIVAPercibido() {
			return totalIVAPercibido;
		}

		public void setTotalIVAPercibido(CRBigDecimal totalIVAPercibido) {
			this.totalIVAPercibido = totalIVAPercibido;
		}

		public CRBigDecimal getTotalDiario() {
			return totalDiario;
		}

		public void setTotalDiario(CRBigDecimal totalDiario) {
			this.totalDiario = totalDiario;
		}

		public boolean isShowsTax() {
			return showsTax;
		}

		public void setShowsTax(boolean showsTax) {
			this.showsTax = showsTax;
		}

		public boolean isSaleSupport() {
			return saleSupport;
		}

		public void setSaleSupport(boolean saleSupport) {
			this.saleSupport = saleSupport;
		}

		public boolean isCounterpart() {
			return counterpart;
		}

		public void setCounterpart(boolean counterpart) {
			this.counterpart = counterpart;
		}
	}

	@Test
	public void testquery7() throws Exception {
		FormadepagopuntoagilJpaController fdppa = new FormadepagopuntoagilJpaController(emf);

		VPosAdditionalData tmp = fdppa.findByTransactionId(new Long(1232));

		if (tmp != null) {
			System.out.println("Registro encontrado");
		} else {
			System.out.println("Registro NO encontrado");
		}

	}

	@Test
	public void testquery8() throws Exception {
		TransaccionJpaController fdppa = new TransaccionJpaController(emf);

		Transaction tmp = fdppa.findLastTransactionByTransactionType_Client("17892041", SourceTransactionType.Credits);

		if (tmp != null) {
			System.out.println("Registro encontrado");
		} else {
			System.out.println("Registro NO encontrado");
		}

	}

	@Test
	public void testquery9() throws Exception {
		FormadepagopuntoagilJpaController fdppa = new FormadepagopuntoagilJpaController(emf);

		VPosAdditionalData tmp = fdppa.findLastFormadepagopuntoagilByVtid("E015C004");

		if (tmp != null) {
			System.out.println("Registro encontrado");
		} else {
			System.out.println("Registro NO encontrado");
		}

	}
}
