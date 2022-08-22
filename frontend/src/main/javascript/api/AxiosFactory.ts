import axios, { AxiosInstance } from 'axios';

class AxiosFactory {
    public readonly axiosInstance: AxiosInstance;

    constructor() {
        this.axiosInstance = axios.create();
    }
}

export const axiosFactory = new AxiosFactory();
