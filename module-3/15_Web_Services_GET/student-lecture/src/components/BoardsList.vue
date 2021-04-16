<template>
  <div>
    <h1>My Kanban Boards</h1>
    <div class="boards">
      <div class="loading" v-if="isLoading">
        <img src="../assets/squirrel.gif" />
      </div>
      <div
        class="board"
        v-for="board in boards"
        v-bind:key="board.id"
        v-bind:style="{ 'background-color': board.backgroundColor }"
        v-else
      >
      <!-- router-link connects a link to a Vue router path
           v-bind:to="path or path name for the link"       
           params: {variable : value} adds a path variable to the path         
           this router-link specifies the path: /board/:id             -->
        <router-link v-bind:to="{ name: 'Board', params: { id: board.id } }">
          {{ board.title }}
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import boardsService from '../services/BoardService';

export default {
  data() {
    return {
      boards: [],
      isLoading: true
    };
  },
  created() {
    boardsService.getBoards().then(response => {
      this.boards = response.data;
      this.isLoading = false;
    });
  }
};
</script>

<style scoped>
.boards {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.board {
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 40px;
  flex: 1;
  margin: 0 20px;
  text-align: center;
}
.loading {
  flex: 3;
}
.board a:link,
.board a:visited {
  color: rgb(243, 243, 243);
  text-decoration: none;
}
</style>
