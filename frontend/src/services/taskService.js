import api from './api';

const taskService = {
  getAllTasks: async () => {
    const response = await api.get('/tasks');
    return response.data;
  },

  getTaskById: async (id) => {
    const response = await api.get(`/tasks/${id}`);
    return response.data;
  },

  getTasksByUser: async (userId) => {
    const response = await api.get(`/tasks/assigned/${userId}`);
    return response.data;
  },

  getTasksByStatus: async (status) => {
    const response = await api.get(`/tasks/status/${status}`);
    return response.data;
  },

  createTask: async (taskData) => {
    const response = await api.post('/tasks', taskData);
    return response.data;
  },

  updateTask: async (id, taskData) => {
    const response = await api.put(`/tasks/${id}`, taskData);
    return response.data;
  },

  deleteTask: async (id) => {
    await api.delete(`/tasks/${id}`);
  },
};

export default taskService;
