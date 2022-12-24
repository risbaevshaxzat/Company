package com.example.lesson_1_task_1.service;

import com.example.lesson_1_task_1.entity.Address;
import com.example.lesson_1_task_1.payload.AddressDto;
import com.example.lesson_1_task_1.payload.ApiResponse;
import com.example.lesson_1_task_1.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public ApiResponse addAddress(AddressDto addressDto) {

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHouseNumber(addressDto.getHouseNumber());
        addressRepository.save(address);
        return new ApiResponse("Address added", true);

    }

    public List<Address> getAddresses() {

        List<Address> addressList = addressRepository.findAll();
        return addressList;
    }

    public Address getAddressById(Integer id) {

        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public ApiResponse editAddress(Integer id, AddressDto addressDto) {

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            return new ApiResponse("Address not found", false);
        }
        Address editingAddress = optionalAddress.get();
        editingAddress.setStreet(addressDto.getStreet());
        editingAddress.setHouseNumber(addressDto.getHouseNumber());
        addressRepository.save(editingAddress);
        return new ApiResponse("Address edited", true);
    }

    public ApiResponse deleteAddress(Integer id) {

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            return new ApiResponse("Address not found", false);
        }
        addressRepository.deleteById(id);
        return new ApiResponse("Address deleted", true);
    }


}
