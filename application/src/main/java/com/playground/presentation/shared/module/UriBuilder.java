package com.playground.presentation.shared.module;

import java.net.URI;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public final class UriBuilder {
  private static final String ID_PATH_SEGMENT = "/{id}";

  public URI buildUri(final String id) {
    return ServletUriComponentsBuilder.fromCurrentRequest()
        .path(ID_PATH_SEGMENT)
        .buildAndExpand(id)
        .toUri();
  }
}
