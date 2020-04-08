package com.example.foundation.domain

public interface DomainToViewMapper<in Domain, out View> {

   fun transform(domainModel: Domain): View
}