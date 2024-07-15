package Converter;

public interface Converter<Domain, DTO> {
    Domain toDomain(DTO dto);

    DTO toDto(Domain domain);
}
