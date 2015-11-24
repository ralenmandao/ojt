import groovy.sql.Sql;

class Exercise4 {
	static void main(args){
		def db = [url: "jdbc:postgresql://localhost:5432/sampledb" , user: 'ralen.mandap', pass: '', driver: 'org.postgresql.Driver']
		def currentTable = 'sampledb'
		def sql = Sql.newInstance(db.url, db.user, db.pass, db.driver)
		println "current table: ${currentTable}" 
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
}
