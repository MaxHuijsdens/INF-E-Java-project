databaseChangeLog() {

    changeSet(author: "bastiaan.klein@42.nl", id: "1") {
        comment("Initialize the base database")
        
        createTable(tableName: "customer") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_customer")
            }
            column(name: "name", type: "VARCHAR(100)") { constraints(nullable: false) }
        }
        addUniqueConstraint(tableName: "customer", columnNames: "name", constraintName: "unq_customer")
        
        createTable(tableName: "supplier") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_supplier")
            }
            column(name: "name", type: "VARCHAR(100)") { constraints(nullable: false) }
        }
        addUniqueConstraint(tableName: "supplier", columnNames: "name", constraintName: "unq_supplier")
        
        createTable(tableName: "sla") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_sla")
            }
            column(name: "number", type: "VARCHAR(20)") { constraints(nullable: false) }
            column(name: "name", type: "VARCHAR(100)") { constraints(nullable: true) }
            column(name: "customer_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_sla_customer", references: "customer(id)") 
            }
        }
        addUniqueConstraint(tableName: "sla", columnNames: "number", constraintName: "unq_sla")
        
        createTable(tableName: "server") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_server")
            }
            column(name: "ipv4", type: "VARCHAR(15)") { constraints(nullable: true) }
            column(name: "ipv6", type: "VARCHAR(39)") { constraints(nullable: true) }
            column(name: "host_name", type: "VARCHAR(100)") { constraints(nullable: false) }
            column(name: "support_name", type: "VARCHAR(100)") { constraints(nullable: true) }
            column(name: "host_dns", type: "VARCHAR(100)") { constraints(nullable: true) }
            column(name: "image", type: "VARCHAR(100)") { constraints(nullable: true) }
            column(name: "cost", type: "DOUBLE") { constraints(nullable: true) }
            column(name: "otap", type: "VARCHAR(1)") { constraints(nullable: true) }
            column(name: "total_ram", type: "INT") { constraints(nullable: true) }
            column(name: "total_disk_size", type: "INT") { constraints(nullable: true) }
            column(name: "cores", type: "INT") { constraints(nullable: true) }
            column(name: "confluence_page_url", type: "VARCHAR(255)") { constraints(nullable: true) }
            column(name: "purpose", type: "VARCHAR(255)") { constraints(nullable: true) }
            column(name: "remarks", type: "VARCHAR(255)") { constraints(nullable: true) }
            column(name: "monitored_by_servercheck", type: "BOOLEAN", defaultValueBoolean: false) { constraints(nullable: true) }
            column(name: "in_hybernation", type: "BOOLEAN", defaultValueBoolean: false) { constraints(nullable: true) }
            column(name: "monitored_by_newrelic", type: "BOOLEAN", defaultValueBoolean: false) { constraints(nullable: true) }
            column(name: "sla_id", type: "BIGINT") { 
                constraints(nullable: true, foreignKeyName: "fk_server_sla", references: "sla(id)") 
            }
            column(name: "supplier_id", type: "BIGINT") { 
                constraints(nullable: true, foreignKeyName: "fk_sla_supplier", references: "supplier(id)") 
            }
        }
        addUniqueConstraint(tableName: "server", columnNames: "host_name", constraintName: "unq_server")
             
        createTable(tableName: "login_user") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_login_user")
            }
            column(name: "name", type: "VARCHAR(50)") { constraints(nullable: false) }
            column(name: "known", type: "BOOLEAN", defaultValueBoolean: false) { constraints(nullable: false) }
        }
        addUniqueConstraint(tableName: "login_user", columnNames: "name", constraintName: "unq_login_user")
        
        createTable(tableName: "server_user") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_server_user")
            }
            column(name: "server_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_server_user_server", references: "server(id)") 
            }
            column(name: "user_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_server_user_user", references: "login_user(id)") 
            }
            column(name: "sudo", type: "BOOLEAN", defaultValueBoolean: false) { constraints(nullable: false) }
        }
        addUniqueConstraint(tableName: "server_user", columnNames: "server_id, user_id", constraintName: "unq_server_user")
        
        createTable(tableName: "server_user_change") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_server_user_change")
            }
            column(name: "server_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_server_user_change_server", references: "server(id)") 
            }
            column(name: "user_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_server_user_change_user", references: "login_user(id)") 
            }
            column(name: "join_or_left", type: "BOOLEAN") { constraints(nullable: false) }
            column(name: "time_of_measurement", type: "TIMESTAMP") { constraints(nullable: false) }
        }
        addUniqueConstraint(tableName: "server_user_change", columnNames: "server_id, user_id, join_or_left, time_of_measurement", constraintName: "unq_server_user_change")
    
        createTable(tableName: "version") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_version")
            }
            column(name: "name", type: "VARCHAR(100)") { constraints(nullable: false) }
            column(name: "version_number", type: "VARCHAR(100)") { constraints(nullable: false) }
        }
        addUniqueConstraint(tableName: "version", columnNames: "name, version_number", constraintName: "unq_version")
        
        createTable(tableName: "server_version") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_server_version")
            }
            column(name: "server_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_server_version_server", references: "server(id)") 
            }
            column(name: "version_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_server_version_version", references: "version(id)") 
            }
            column(name: "time_of_measurement", type: "TIMESTAMP") { constraints(nullable: false) }
        }
        addUniqueConstraint(tableName: "server_version", columnNames: "server_id, version_id, time_of_measurement", constraintName: "unq_server_version")
        
        createTable(tableName: "persistent_server_data") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_persistent_server_data")
            }
            column(name: "server_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_pers_data_server", references: "server(id)") 
            }
            column(name: "time_of_measurement", type: "TIMESTAMP") { constraints(nullable: false) }
            column(name: "ufw_status", type: "VARCHAR(20)") { constraints(nullable: false) }
            column(name: "packages_can_updated", type: "INT") { constraints(nullable: false) }
            column(name: "packages_security", type: "INT") { constraints(nullable: false) }
            column(name: "disk_size", type: "INT") { constraints(nullable: false) }
            column(name: "restart_needed", type: "BOOLEAN", defaultValueBoolean: false) { constraints(nullable: false) }
            column(name: "script_error_occured", type: "BOOLEAN", defaultValueBoolean: false) { constraints(nullable: false) }            
        }
        
        createTable(tableName: "measurement") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_measurements")
            }
            column(name: "server_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_measurements_server", references: "server(id)") 
            }
            column(name: "data_source", type: "VARCHAR(10)")
            column(name: "time_of_measurement", type: "TIMESTAMP") { constraints(nullable: false) }
            column(name: "load", type: "DOUBLE")
            column(name: "cpu_usage", type: "DOUBLE")
            column(name: "cpu_usage_percent", type: "DOUBLE")
            column(name: "disk_usage", type: "DOUBLE")
            column(name: "disk_usage_percent", type: "DOUBLE")
            column(name: "network_in", type: "DOUBLE")
            column(name: "network_out", type: "DOUBLE")
            column(name: "disk_io", type: "DOUBLE")
            column(name: "roundtrip_time", type: "DOUBLE")
            column(name: "io_wait", type: "DOUBLE")
            column(name: "free_ram", type: "DOUBLE")
            column(name: "free_swap", type: "DOUBLE")
            column(name: "processes_running", type: "INT")
            column(name: "total_memory", type: "DOUBLE")
            column(name: "inode_usage", type: "DOUBLE")
            column(name: "uptime", type: "VARCHAR(50)") { constraints(nullable: false) }
            column(name: "status", type: "VARCHAR(50)")  
        }
        createIndex(tableName: "measurement", indexName: "index_measurement_server_id") {
            column(name: 'server_id, time_of_measurement, data_source')
        }
             
        createTable(tableName: "note") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_note")
            }
            column(name: "server_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_note_server", references: "server(id)") 
            }
            column(name: "reporter", type: "VARCHAR(100)") { constraints(nullable: false) }
            column(name: "note_text", type: "TEXT") { constraints(nullable: false) }
            column(name: "incident_date", type: "TIMESTAMP") { constraints(nullable: false) }
            column(name: "creation_date", type: "TIMESTAMP") { constraints(nullable: false) }
        }
        
        createTable(tableName: "iptable") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_iptable")
            }
            column(name: "table_rule", type: "VARCHAR(50)") { constraints(nullable: false) }
            column(name: "known", type: "BOOLEAN", defaultValueBoolean: false) { constraints(nullable: false) }
        }
        addUniqueConstraint(tableName: "iptable", columnNames: "table_rule", constraintName: "unq_iptable")
        
        createTable(tableName: "server_iptable") {
            column(autoIncrement: true, name: "id", type: "BIGINT") {
                constraints(nullable: false, primaryKey: true, primaryKeyName: "pk_server_iptable")
            }
            column(name: "server_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_server_iptable_server", references: "server(id)") 
            }
            column(name: "iptable_id", type: "BIGINT") { 
                constraints(nullable: false, foreignKeyName: "fk_server_iptable_iptable", references: "iptable(id)") 
            }
        }
        addUniqueConstraint(tableName: "server_iptable", columnNames: "server_id, iptable_id", constraintName: "unq_server_iptable")

        createView(viewName: "current_database_version") {
            """
                SELECT id, tag, author, dateexecuted AS date_executed, filename AS file_name, comments
                FROM databasechangelog
                WHERE orderexecuted = (SELECT MAX(orderexecuted) FROM databasechangelog WHERE tag IS NOT NULL)
            """
        }
    }

    changeSet(author: "bastiaan.klein@42.nl", id: "2") {
        comment("Tag the database")
        tagDatabase(tag: "1")
    }

}