import { IRestaurant } from "@/lib/types";
import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8080/api/restaurants",
  headers: {
    "Content-Type": "application/json",
  },
});

interface PaginatedResponse<T> {
  restaurants: T[];
  currentPage: number;
  totalPages: number;
  totalItems: number;
}

export const getRestaurants = async (
  page: string
): Promise<PaginatedResponse<IRestaurant> | void> => {
  try {
    const response = await instance.get(`?page=${page}&size=6`);
    const data = response.data;
    if (data && Array.isArray(data.content)) {
      return {
        restaurants: data.content as IRestaurant[],
        currentPage: data.number ?? 0,
        totalPages: data.totalPages ?? 0,
        totalItems: data.totalElements ?? 0,
      };
    }
    throw new Error("Unexpected response format");
  } catch (err) {
    console.error(err);
    // Handle the error appropriately here
  }
};

export const getRestaurantById = async (
  id: string
): Promise<IRestaurant | void> => {
  try {
    const response = await instance.get(`/${id}`);
    if (response.data) {
      return response.data as IRestaurant;
    }
    throw new Error("Unexpected response format");
  } catch (err) {
    console.error(err);
    // Handle the error appropriately here
  }
};

export const searchRestaurants = async (
  searchParam: string
): Promise<PaginatedResponse<IRestaurant> | void> => {
  try {
    const response = await instance.get(`/search/city?city=${searchParam}`);
    if (Array.isArray(response.data)) {
      return {
        restaurants: response.data as IRestaurant[],
        currentPage: 0,
        totalPages: 0,
        totalItems: response.data.length,
      };
    }
    throw new Error("Unexpected response format");
  } catch (err) {
    console.error(err);
    // Handle the error appropriately here
  }
};

export const searchRestaurantsByName = async (
  searchParam: string
): Promise<PaginatedResponse<IRestaurant> | void> => {
  try {
    const response = await instance.get(`/search?name=${searchParam}`);
    if (Array.isArray(response.data)) {
      return {
        restaurants: response.data as IRestaurant[],
        currentPage: 0,
        totalPages: 0,
        totalItems: response.data.length,
      };
    }
    throw new Error("Unexpected response format");
  } catch (err) {
    console.error(err);
    // Handle the error appropriately here
  }
};

export const searchNearbyRestaurants = async (
  latitude: string,
  longitude: string
): Promise<PaginatedResponse<IRestaurant> | void> => {
  try {
    const response = await instance.get(
      `/search/location?latitude=${latitude}&longitude=${longitude}`
    );
    if (Array.isArray(response.data)) {
      return {
        restaurants: response.data as IRestaurant[],
        currentPage: 0,
        totalPages: 0,
        totalItems: response.data.length,
      };
    }
    throw new Error("Unexpected response format");
  } catch (err) {
    console.error(err);
    // Handle the error appropriately here
  }
};

export const searchByImage = async (
  file: File
): Promise<PaginatedResponse<IRestaurant> | void> => {
  try {
    const formData = new FormData();
    formData.append("file", file);
    const response = await instance.post(`/search/image`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    if (Array.isArray(response.data)) {
      return {
        restaurants: response.data as IRestaurant[],
        currentPage: 0,
        totalPages: 0,
        totalItems: response.data.length,
      };
    }
    throw new Error("Unexpected response format");
  } catch (err) {
    console.error(err);
    // Handle the error appropriately here
  }
};
