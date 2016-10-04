package guru.springframework.services.reposervices;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import guru.springframework.services.ProductService;

@Service
@Profile({ "springdatajpa", "jpadao" })
public class ProductServiceRepoImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public List<?> listAll() {
		return StreamSupport.stream(repository.findAll().spliterator(), false).collect(toList());
	}

	@Override
	public Product getById(Integer id) {
		return repository.findOne(id);
	}

	@Override
	public Product saveOrUpdate(Product domainObject) {
		return repository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

}
