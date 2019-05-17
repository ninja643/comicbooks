package rs.ac.ni.pmf.marko.comics.server.datamodel.converter;

public interface IConverter<D, E>
{
	D dtoFromEntity(E entity);
	E entityFromDto(D dto);
}
