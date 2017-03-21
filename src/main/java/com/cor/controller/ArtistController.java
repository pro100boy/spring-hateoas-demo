package com.cor.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.cor.domain.Artist;
import com.cor.service.MusicService;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtistController {
	@Autowired
	private MusicService musicService;

	@GetMapping(value = "/artist/{id}")
	public Resource<Artist> getArtist(@PathVariable(value = "id") String id) {
		Artist a = musicService.getArtist(id);
		Resource<Artist> resource = new Resource<Artist>(a);
		resource.add(linkTo(methodOn(ArtistController.class).getArtist(id)).withSelfRel());
		return resource;
	}
}