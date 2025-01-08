package com.real.estate.repository;

import com.real.estate.entity.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepository extends JpaRepository<Testimonial,Long> {
}
