import React, { useState, useEffect } from "react";
import { Paper, InputBase, IconButton, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import SearchIcon from "@mui/icons-material/Search";

export default function Searchbox() {
  let navigate = useNavigate();
  const [search, setSearch] = useState();
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/feed")
      .then((res) => {
        console.log(res.data);
        setData(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [search]);

  const getValue = (e) => {
    setSearch(e.target.value.toLowerCase());
  };

  const searched = data.filter((item) =>
    item.title.toLowerCase().includes(search)
  );
  return (
    <>
      <Paper
        component="form"
        sx={{
          p: "2px 4px",
          display: "flex",
          alignItems: "center",
          width: 800,
          margin: "auto",
        }}
      >
        <InputBase
          sx={{ ml: 1, flex: 1 }}
          placeholder="검색어를 입력해주세요"
          inputProps={{ "aria-label": "search google maps" }}
          value={search}
          onChange={getValue}
        />
        <IconButton type="button" sx={{ p: "10px" }} aria-label="search">
          <SearchIcon />
        </IconButton>
      </Paper>
      <Paper
        sx={{
          alignItems: "center",
          margin: "auto",
          marginTop: "0.4rem",
        }}
      >
        {search
          ? searched.map((i) => {
              return (
                <Button
                  onClick={() => {
                    navigate("/feeddetail/" + i.id);
                  }}
                >
                  <Paper
                    sx={{
                      p: "2px 4px",
                      alignItems: "center",
                      margin: "auto",
                      marginTop: "0.4rem",
                      padding: 1,
                    }}
                  >
                    {i.title}
                  </Paper>
                </Button>
              );
            })
          : null}
      </Paper>
    </>
  );
}
