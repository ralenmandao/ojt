import groovy.sql.Sql


class Postgre {
	static void main(args){
		
		def db = [url:'jdbc:postgresql://localhost:5432/sampledb', user: "ralen.mandap", pass: '', driver:'org.postgresql.Driver']
		def sql = Sql.newInstance(db.url, db.user, db.pass, db.driver)
	
		
		//DELETE all rows
		sql.execute("DELETE FROM sample_tbl")

		println "current table: sample_tbl"
		
		println "current table size: ${sql.firstRow( 'SELECT COUNT(*) FROM sample_tbl' ).count}"
		
		//INSERT Query
//		sql.executeInsert("INSERT INTO sample_tbl(id, data_name, data1, data2) VALUES(1,'test',2,2)")
//		sql.executeInsert("INSERT INTO sample_tbl(id, data_name, data1, data2) VALUES(2,'test5',3,6)")
//		sql.execute("INSERT INTO sample_tbl(id, data_name, data1, data2) VALUES(3,'test6',2,3)")
//		sql.execute("INSERT INTO sample_tbl(id, data_name, data1, data2) VALUES(4,'test5',3,5)")
		

		
		//INSERT BATCH
		sql.withBatch("INSERT INTO sample_tbl(id, data_name, data1, data2) VALUES(?,?,?,?)"){ 
			ps -> ps.addBatch([1,'test',2,2]) 
				ps.addBatch([2,'test5',3,6])
				ps.addBatch(3,'test6',2,3)
				ps.addBatch(4,'test5',3,5)
			}
		
		println "current table size: ${sql.firstRow( 'SELECT COUNT(*) FROM sample_tbl' ).count}"
		
		
		//UPDATE
//		sql.executeUpdate("UPDATE sample_tbl set data_name = 'test' where data_name LIKE 'test%'")
		

		//SHOW all
		printf "%-5s %-15s %-10s %s \n", 'id', 'data_name', 'data1', 'data2'
		sql.eachRow( "SELECT * FROM sample_tbl ORDER BY id", { printf "%-5s %-17.5s %-10s %s \n", it.id, it.data_name, it.data1, it.data2 })
		
		def printSQL = sql.rows("SELECT * FROM sample_tbl ORDER BY id")
//		println printSQL.join('\n')
	}
}
