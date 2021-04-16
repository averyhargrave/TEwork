<template>
  <div class="card" v-bind:class="{read : book.read}" v-bind:key="book.title">
    <h2 class="book-title"> {{book.title}} </h2>
    <img v-if="book.isbn" v-bind:src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'" />
    <h3 class="book-author"> {{book.author}} </h3>
    <button 
        type="button"
        v-bind:key="book.read"
        v-on:click="readButton"
        >{{book.read ? "Unread" : "Read"}} </button>
  </div>
</template>

<script>
export default {
    name: 'book-card',
    props: ["book"],
    methods: {
        readButton() {
            this.$store.commit('FLIP_READ', this.book)
        }
    }
}
</script>

<style>
.card {
    border: 2px solid black;
    border-radius: 10px;
    width: 250px;
    height: 550px;
    margin: 20px;
}
.card.read {
    background-color: lightgray;
}
.card .book-title {
    font-size: 1.5rem;
}
.card .book-author {
    font-size: 1rem;
}
</style>