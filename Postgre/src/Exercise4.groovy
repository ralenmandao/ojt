import groovy.sql.Sql;

class Exercise4 {
	
	// Can't touch this
	private static final String dbname = 'sampledb';
	private static final def db = [url: "jdbc:postgresql://localhost:5432/${dbname}" , user: 'ralen.mandap', pass: '', driver: 'org.postgresql.Driver']
	private static final def sql = Sql.newInstance(db.url, db.user, db.pass, db.driver)
	
	static void main(args){
		// Create the magical db
		sql.rows("SELECT datname FROM pg_database WHERE datname = ${dbname}") ?: createDB() 
		createTable()
		
		println "current table: sampledb" 
		def numberOfRecords = {
			sql.firstRow("SELECT COUNT(*) as num FROM sampledb").num
		}
		println "current table size: ${numberOfRecords()}"
		
		def rows = []
		rows << ['test',2,2,'in progress']
		rows << ['test5',3,6,'in progress']
		rows << ['test6',2,3,'in progress']
		rows << ['test5',3,5,'in progress']
		
		sql.withBatch(4, """INSERT INTO sampledb(data_name,data1,data2,status)
							VALUES(?,?,?,?)	
						 """){ ps -> rows.each{ ps.addBatch(it) } }
						 
		println "current table size: ${numberOfRecords()}"
		
	}
	
	static def createTable(){
		sql.execute("""
			CREATE DATABASE sampledb;
			CREATE TABLE IF NOT EXISTS sampledb(
			   ID SERIAL PRIMARY KEY    NOT NULL,
			   DATA_NAME VARCHAR(45)    ,
			   DATA1     INT    	    ,
			   DATA2     INT		    ,
			   status    VARCHAR(20)
			);
		""")
	}
	
	static def createDB(){
		sql.execute("CREATE DATABASE sampledb;")
	}
}
