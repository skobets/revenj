package gen.model.test;



public final class FindMany   implements java.io.Serializable, org.revenj.patterns.Report<FindMany.Result> {
	
	

public static class Result   {
	
	
	@com.fasterxml.jackson.annotation.JsonCreator 
	private Result(
			@com.fasterxml.jackson.annotation.JsonProperty("composites")  final java.util.List<gen.model.test.CompositeList> composites,
			@com.fasterxml.jackson.annotation.JsonProperty("found")  final gen.model.test.Composite found) {
			
		this.composites = composites == null ? new java.util.ArrayList<gen.model.test.CompositeList>(4) : composites;
		this.found = found == null ? new gen.model.test.Composite() : found;
	}

	
	private final java.util.List<gen.model.test.CompositeList> composites;

	
	@com.fasterxml.jackson.annotation.JsonProperty("composites")
	public java.util.List<gen.model.test.CompositeList> getComposites()  {
		
		return this.composites;
	}

	
	private final gen.model.test.Composite found;

	
	@com.fasterxml.jackson.annotation.JsonProperty("found")
	public gen.model.test.Composite getFound()  {
		
		return this.found;
	}

}

	
	@com.fasterxml.jackson.annotation.JsonCreator 
	public FindMany(
			@com.fasterxml.jackson.annotation.JsonProperty("id")  final java.util.UUID id,
			@com.fasterxml.jackson.annotation.JsonProperty("ids")  final java.util.Set<java.util.UUID> ids) {
			
		if(id != null) setId(id); else this.id = java.util.UUID.randomUUID();
		if(ids != null) setIds(ids); else this.ids = new java.util.HashSet<java.util.UUID>(4);
	}

	
	
	public FindMany() {
			
		this.id = java.util.UUID.randomUUID();
		this.ids = new java.util.HashSet<java.util.UUID>(4);
	}

	private static final long serialVersionUID = 4535092221242796340L;
	
	private java.util.UUID id;

	
	@com.fasterxml.jackson.annotation.JsonProperty("id")
	public java.util.UUID getId()  {
		
		return id;
	}

	
	public FindMany setId(final java.util.UUID value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"id\" cannot be null!");
		this.id = value;
		
		return this;
	}

	
	private java.util.Set<java.util.UUID> ids;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ids")
	public java.util.Set<java.util.UUID> getIds()  {
		
		return ids;
	}

	
	public FindMany setIds(final java.util.Set<java.util.UUID> value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"ids\" cannot be null!");
		org.revenj.Guards.checkNulls(value);
		this.ids = value;
		
		return this;
	}

	
	public Result populate(org.revenj.patterns.ServiceLocator locator) {
		java.sql.Connection connection = locator.resolve(java.sql.Connection.class);
		try (java.sql.PreparedStatement ps = connection.prepareStatement("SELECT \"test\".\"FindMany\"(?, ?)")) {
			int index = 1;
			
			ps.setObject(index, this.id);
			index++;
			
			{
				Object[] __arr = new Object[this.ids.size()];
				int __ind = 0;
				for (Object __it : this.ids) __arr[__ind++] = __it;
				ps.setArray(index, connection.createArrayOf("uuid", __arr));
			}
			index++;
			
			org.revenj.postgres.PostgresReader reader = new org.revenj.postgres.PostgresReader(locator);
			java.sql.ResultSet rs = ps.executeQuery();
			rs.next();
			reader.process(rs.getString(1));
			rs.close();
			reader.read(2);
			
			java.util.List<gen.model.test.CompositeList> _list_composites = org.revenj.postgres.converters.ArrayTuple.parse(reader, 1, locator.resolve(gen.model.test.converters.CompositeListConverter.class)::from); 
			java.util.List<gen.model.test.CompositeList> _composites_ = _list_composites != null ? _list_composites : null;
			gen.model.test.Composite _found_ = locator.resolve(gen.model.test.converters.CompositeConverter.class).from(reader, 1);
			return new Result(_composites_, _found_);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
