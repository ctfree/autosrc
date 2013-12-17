<#list infos as info>
	public void delete${info.key1}(String ${info.key2},long ts)
			throws IOException {
		SqlSession session = null;

		byte eTagHash = PostTable.computeETagHash(${info.key2});
		String etagHashVal = PostSqlUtil.getHashString(new byte[]{eTagHash}, true);
		int dbId = 0;

		try {
			SqlSessionFactory sessionFactory = DaoFactory
					.getPostSqlSessionFactory(dbId);
			session = sessionFactory.openSession();

			${info.key3}Key m${info.key4} = new ${info.key3}Key();
			m${info.key4}.setEtaghash(etagHashVal);
			m${info.key4}.set${info.key2?cap_first}(${info.key2});
			m${info.key4}.setTs(ts);

			session.delete("sql.${info.key3}Mapper.deleteByPrimaryKey", m${info.key4});
			session.commit();
		} 
		catch (IOException e) {
			logger.error("delete${info.key1} error : ", e);
			throw e;
		} 
		finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void add${info.key1}List(List<${info.key3}> ${info.key5}List) throws IOException {
		SqlSession session = null;
		int dbId = 0;
		try {
			SqlSessionFactory sessionFactory = DaoFactory
					.getPostSqlSessionFactory(dbId);
			session = sessionFactory.openSession();

			HashMap params = new HashMap();
            params.put("${info.key5}List", ${info.key5}List);
            
            session.insert("sql.${info.key3}Mapper.insertByList", params);

			session.commit();
            	
		} 
		catch (IOException e) {
			logger.error("scan${info.key1} error : ", e);
			throw e;
		} 
		finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void delete${info.key1}List(List<${info.key3}> ${info.key5}List) throws IOException {
		SqlSession session = null;
		int dbId = 0;
		try {
			SqlSessionFactory sessionFactory = DaoFactory
					.getPostSqlSessionFactory(dbId);
			session = sessionFactory.openSession();

			HashMap params = new HashMap();
            params.put("${info.key5}List", ${info.key5}List);
            
            session.insert("sql.${info.key3}Mapper.deleteByList", params);

			session.commit();
            	
		} 
		catch (IOException e) {
			logger.error("delete${info.key1} error : ", e);
			throw e;
		} 
		finally {
			if (session != null) {
				session.close();
			}
		}
	}
</#list>