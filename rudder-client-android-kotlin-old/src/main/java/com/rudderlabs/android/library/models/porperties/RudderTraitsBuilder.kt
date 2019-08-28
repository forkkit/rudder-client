package com.rudderlabs.android.library.models.porperties

import com.rudderlabs.android.library.models.RudderTraits
import com.rudderlabs.android.library.models.TraitsAddress
import com.rudderlabs.android.library.models.TraitsCompany

class RudderTraitsBuilder {
    private var city: String = ""
    fun setCity(city: String): RudderTraitsBuilder {
        this.city = city
        return this
    }

    private var country: String = ""
    fun setCountry(country: String): RudderTraitsBuilder {
        this.country = country
        return this
    }

    private var postalCode: String = ""
    fun setPostalCode(postalCode: String): RudderTraitsBuilder {
        this.postalCode = postalCode
        return this
    }

    private var state: String = ""
    fun setState(state: String): RudderTraitsBuilder {
        this.state = state
        return this
    }

    private var street: String = ""
    fun setStreet(street: String): RudderTraitsBuilder {
        this.street = street
        return this
    }

    private var age: Int = 0
    fun setAge(age: Int): RudderTraitsBuilder {
        this.age = age
        return this
    }

    private var birthday: String = ""
    fun setBirthday(birthday: String): RudderTraitsBuilder {
        this.birthday = birthday
        return this
    }

    private var companyName: String = ""
    fun setCompanyName(companyName: String): RudderTraitsBuilder {
        this.companyName = companyName
        return this
    }

    private var companyId: String = ""
    fun setCompanyId(companyId: String): RudderTraitsBuilder {
        this.companyId = companyId
        return this
    }

    private var industry: String = ""
    fun setIndustry(industry: String): RudderTraitsBuilder {
        this.industry = industry
        return this
    }

    private var createdAt: String = ""
    fun setCreatedAt(createdAt: String): RudderTraitsBuilder {
        this.createdAt = createdAt
        return this
    }

    private var description: String = ""
    fun setDescription(description: String): RudderTraitsBuilder {
        this.description = description
        return this
    }

    private var email: String = ""
    fun setEmail(email: String): RudderTraitsBuilder {
        this.email = email
        return this
    }

    private var firstName: String = ""
    fun setFirstName(firstName: String): RudderTraitsBuilder {
        this.firstName = firstName
        return this
    }

    private var gender: String = ""
    fun setGender(gender: String): RudderTraitsBuilder {
        this.gender = gender
        return this
    }

    private var id: String = ""
    fun setId(id: String): RudderTraitsBuilder {
        this.id = id
        return this
    }

    private var lastName: String = ""
    fun setLastName(lastName: String): RudderTraitsBuilder {
        this.lastName = lastName
        return this
    }

    private var name: String = ""
    fun setName(name: String): RudderTraitsBuilder {
        this.name = name
        return this
    }

    private var phone: String = ""
    fun setPhone(phone: String): RudderTraitsBuilder {
        this.phone = phone
        return this
    }

    private var title: String = ""
    fun setTitle(title: String): RudderTraitsBuilder {
        this.title = title
        return this
    }

    private var userName: String = ""
    fun setUserName(userName: String): RudderTraitsBuilder {
        this.userName = userName
        return this
    }

    fun build(): RudderTraits {
        return RudderTraits().also {
            it.address = TraitsAddress().also { a ->
                a.city = this.city
                a.country = this.country
                a.postalCode = this.postalCode
                a.state = this.state
                a.street = this.street
            }
            it.age = this.age
            it.birthday = this.birthday
            it.company = TraitsCompany().also { c ->
                c.id = this.companyId
                c.industry = this.industry
                c.name = this.companyName
            }
            it.createdAt = this.createdAt
            it.description = this.description
            it.email = this.email
            it.firstName = this.firstName
            it.gender = this.gender
            it.id = this.id
            it.lastName = this.lastName
            it.name = this.name
            it.phone = this.phone
            it.title =this.title
            it.userName = this.userName
        }
    }
}