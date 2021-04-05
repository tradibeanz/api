package fr.esgi.beanz.api.portfolio;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/portfolios")
@RequiredArgsConstructor
public class PortfolioController {
  @Autowired
  private final PortfolioService portfolioService;

  @GetMapping
  public List<Portfolio> getRepository() {
    return portfolioService.getPortFolios();
  }

  @GetMapping(value = "/{id}")
  public Portfolio getPortfolio(@PathVariable("id") long id) {
    final var portfolio = portfolioService.getPortfolio(id);

    return portfolio.get();
  }

  @RequestMapping(method = RequestMethod.POST)
  public String postPortfolio(@Valid() @RequestBody() Portfolio portfolio) {

    final var res = portfolioService.createPortfolio(portfolio);
    return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + res.getId();
  }

}
